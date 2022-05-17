package io.kuzzle.sdk.controllers

import io.kuzzle.sdk.Kuzzle
import io.kuzzle.sdk.coreClasses.maps.KuzzleMap
import io.kuzzle.sdk.coreClasses.responses.Response
import io.kuzzle.sdk.events.NetworkStateChangeEvent
import io.kuzzle.sdk.events.RoomMessageEvent
import io.kuzzle.sdk.events.UnhandledResponseEvent
import io.kuzzle.sdk.handlers.NotificationHandler
import io.kuzzle.sdk.protocol.ProtocolState
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class RealtimeController(kuzzle: Kuzzle) : BaseController(kuzzle) {
    private inner class Subscription(
        val index: String,
        val collection: String,
        val filter: Map<String, Any>,
        val handler: (Response) -> Unit,
        val scope: String,
        val users: String,
        val subscribeToSelf: Boolean,
        val volatile: Map<String?, Any?>
    )

    init {
        kuzzle.protocol.addListener<RoomMessageEvent> {event ->
            val response: Response = event.response
            var sdkInstanceId = response.Volatile?.get("sdkInstanceId")?.toString();

            val subscriptions: ArrayList<Subscription>? = currentSubscriptions[response.room]
            if (subscriptions != null) {
                val instanceId = sdkInstanceId
                subscriptions.forEach {
                    if (instanceId == kuzzle.instanceId && it.subscribeToSelf || instanceId != kuzzle.instanceId) {
                        it.handler(response)
                    }
                }
            } else {
                kuzzle.protocol.trigger(UnhandledResponseEvent(response))
            }
        }

        kuzzle.protocol.addListener<NetworkStateChangeEvent> {
            if (it.state == ProtocolState.CLOSE) {
                currentSubscriptions.clear()
            }
        }
    }

    fun count(roomId: String): CompletableFuture<Int> {
        return kuzzle
            .query(
                KuzzleMap().apply {
                    put("controller", "realtime")
                    put("action", "count")
                    put("body", KuzzleMap().put("roomId", roomId))
                }
            )
            .thenApplyAsync { response -> (response.result as KuzzleMap?)!!.getNumber("count")?.toInt() }
    }

    fun publish(index: String, collection: String, message: Map<String?, Any?>): CompletableFuture<Void> {
        return kuzzle
            .query(
                KuzzleMap().apply {
                    put("controller", "realtime")
                    put("action", "publish")
                    put("index", index)
                    put("collection", collection)
                    put("body", KuzzleMap().put("message", message))
                }
            )
            .thenApplyAsync { null }
    }

    fun renewSubscriptions() {
        for ((key, value) in subscriptionsCache) {
            (value).forEach(
                Consumer { subscription: Subscription ->
                    subscribe(
                        subscription.index,
                        subscription.collection,
                        subscription.filter,
                        subscription.scope,
                        subscription.users,
                        subscription.subscribeToSelf,
                        subscription.volatile,
                        subscription.handler
                    )
                }
            )
            subscriptionsCache[key]!!.clear()
        }
    }

    fun subscribe(
        index: String?,
        collection: String?,
        filters: Map<String, Any>,
        scope: String = "all",
        users: String = "all",
        subscribeToSelf: Boolean = true,
        volatiles: Map<String?, Any?> = HashMap(),
        handler: (Response) -> Unit
    ): CompletableFuture<String> {
        val query: KuzzleMap = KuzzleMap().apply {
            put("controller", "realtime")
            put("action", "subscribe")
            put("index", index)
            put("collection", collection)
            put("body", filters)
            put("volatile", volatiles)
        }
        return kuzzle
            .query(query)
            .thenApplyAsync { response ->
                val channel = (response.result as Map<*, *>)["channel"].toString()
                val subscription = Subscription(
                    index!!,
                    collection!!,
                    filters,
                    handler,
                    scope,
                    users,
                    subscribeToSelf,
                    volatiles
                )
                if (currentSubscriptions[channel] == null) {
                    val item = ArrayList<Subscription>()
                    item.add(subscription)
                    currentSubscriptions[channel] = item
                    subscriptionsCache[channel] = item
                } else {
                    currentSubscriptions[channel]!!.add(subscription)
                    subscriptionsCache[channel]!!.add(subscription)
                }
                (response.result as Map<*, *>)["roomId"].toString()
            }
    }

    /**
     For JAVA
     */
    @JvmOverloads
    fun subscribe(
        index: String?,
        collection: String?,
        filters: Map<String, Any>,
        scope: String = "all",
        users: String = "all",
        subscribeToSelf: Boolean = true,
        volatiles: Map<String?, Any?> = HashMap(),
        handler: NotificationHandler
    ): CompletableFuture<String> {
        return subscribe(
            index,
            collection,
            filters,
            scope,
            users,
            subscribeToSelf,
            volatiles
        ) {
            handler.run(it)
        }
    }

    fun unsubscribe(roomId: String): CompletableFuture<Void> {
        return kuzzle
            .query(
                KuzzleMap().apply {
                    put("controller", "realtime")
                    put("action", "unsubscribe")
                    put("body", KuzzleMap().put("roomId", roomId))
                }
            )
            .thenApplyAsync { _ ->
                var subs: ArrayList<Subscription>? = currentSubscriptions[roomId]
                if (subs != null) {
                    currentSubscriptions[roomId]!!.clear()
                }
                subs = subscriptionsCache[roomId]
                if (subs != null) {
                    subscriptionsCache[roomId]!!.clear()
                }
                null
            }
    }

    private val currentSubscriptions = HashMap<String, ArrayList<Subscription>>()
    private val subscriptionsCache = HashMap<String, ArrayList<Subscription>>()
}
