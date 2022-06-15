package io.kuzzle.sdk.protocol

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import io.kuzzle.sdk.coreClasses.exceptions.*
import io.kuzzle.sdk.coreClasses.http.Route
import io.kuzzle.sdk.coreClasses.json.JsonSerializer
import io.kuzzle.sdk.coreClasses.maps.KuzzleMap
import io.kuzzle.sdk.coreClasses.serializer.StringSerializer
import io.kuzzle.sdk.events.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.CompletableFuture

data class ControllerActionRoute(val verb: String, val path: String)

open class Http : AbstractProtocol {
    override var state: ProtocolState = ProtocolState.CLOSE
    private var uri: String
    private var useBuiltRoutes = false
    private val routes = HashMap<String, Route>()

    @JvmOverloads
    constructor(
        host: String,
        port: Int = 7512,
        isSsl: Boolean = false
    ) {
        if (isSsl) {
            this.uri = "https://$host:$port"
        } else {
            this.uri = "http://$host:$port"
        }
        super.addListener(::onLoginAttempt)
    }

    private fun onLoginAttempt(event: LoginAttemptEvent) {
        /**
         * Once the user is logged in we try to fetch and build the routes
         * if not already built.
         */
        if (! useBuiltRoutes && event.success) {
            tryToBuildRoutes()
        }
    }

    /**
     * Try to find the best route for a given action of a controller
     *
     * With same URL size, we prefer the GET route
     * with different URL sizes, we keep the shortest because URL params
     * will be in the query string
     */
    private fun findBestRoute(controller: String, action: String, httpRoutes: List<ControllerActionRoute>): ControllerActionRoute {
        if (httpRoutes.size == 1) {
            return httpRoutes[0]
        }

        /**
         * Search route can also be accessed with GET,
         * but to provide a query for the search we need to use the POST method
         */
        if (controller.toLowerCase() == "document" && action.toLowerCase() == "search") {
            return httpRoutes.find {
                it.verb == "POST"
            } ?: httpRoutes[0]
        }

        var length = httpRoutes[0].path.length
        var selectedRoute = httpRoutes[0]
        var shortestRoute = httpRoutes[0]
        var sameLength = true
        for (route: ControllerActionRoute in httpRoutes) {
            if (route.path.length < length) {
                length = route.path.length
                shortestRoute = route
                sameLength = false
            }

            if (route.verb.toUpperCase() == "GET") {
                selectedRoute = route
            }
        }
        return if (sameLength) selectedRoute else shortestRoute
    }

    /**
     * Given a map of routes from _publicApi
     * construct the best routes for each controller's action
     */
    private fun buildRoutes(routes: Map<String?, Any?>) {
        for (controllerEntry in routes) {
            for (actionEntry in controllerEntry.value as Map<String?, Any?>) {
                val map = actionEntry.value as Map<String?, Any?>

                if (! map.containsKey("controller") || ! map.containsKey("action") || ! map.containsKey("http")) {
                    continue
                }

                val controller = map["controller"] as String
                val action = map["action"] as String

                val httpRoutes = map["http"] as List<Map<String?, Any?>>

                if (httpRoutes.isNotEmpty()) {
                    val route = findBestRoute(
                        controller,
                        action,
                        httpRoutes.map {
                            ControllerActionRoute(it["verb"] as String, it["path"] as String)
                        }
                    )
                    this.routes["$controller:$action"] = Route.parse(route.verb, route.path)
                }
            }
        }
    }

    /**
     * Try to fetch and build the routes
     * if it fails it does not switch the flag "useBuiltRoutes"
     */
    private fun tryToBuildRoutes() {
        val wait = CompletableFuture<Void>()

        GlobalScope.launch {
            val client = HttpClient() {
                expectSuccess = false
            }
            try {
                var response: HttpResponse = client.get("$uri/_publicApi") {
                    this.header("content-type", "application/json")
                    this.body = "{}"
                }

                if (response.status.value != 200) {
                    wait.complete(null)
                    return@launch
                }

                val responseJson = JsonSerializer.deserialize(response.receive()) as Map<String?, Any?>

                buildRoutes(responseJson["result"] as Map<String?, Any?>)
                useBuiltRoutes = true
            } catch (e: Exception) {
                // Do nothing
            } finally {
                client.close()
                wait.complete(null)
            }
        }

        wait.get()
    }

    override fun connect() {
        if (this.state != ProtocolState.CLOSE) {
            return
        }

        val wait = CompletableFuture<Void>()
        // if state is NOT open, request /_query to see if we have the proper rights to make request using _query
        GlobalScope.launch {
            val client = HttpClient() {
                expectSuccess = false
            }
            try {
                val response: HttpResponse = client.post("$uri/_query") {
                    this.header("content-type", "application/json")
                    this.body = "{}"
                }
                if (response.status.value == 401 || response.status.value == 403) {
                    wait.completeExceptionally(java.lang.Exception("You must have permission on the _query route."))
                    return@launch
                }
                wait.complete(null)
            } catch (e: Exception) {
                wait.completeExceptionally(e)
            } finally {
                client.close()
            }
        }

        wait.get()
        tryToBuildRoutes()

        this.state = ProtocolState.OPEN
        trigger(NetworkStateChangeEvent(ProtocolState.OPEN))
    }

    override fun disconnect() {
        if (state != ProtocolState.OPEN) {
            return
        }

        this.state = ProtocolState.CLOSE
        trigger(NetworkStateChangeEvent(ProtocolState.CLOSE))
    }

    /**
     * Make a request to Kuzzle using the _query endpoint
     */
    private fun query(payload: Map<String?, Any?>) {
        GlobalScope.launch { // Launch HTTP Request inside a coroutine to be non-blocking
            val client = HttpClient() {
                expectSuccess = false
            }
            try {
                val response: HttpResponse = client.post("$uri/_query") {
                    if (payload["headers"] != null && payload["headers"] is Map<*, *>) {
                        for (entry in payload["headers"] as Map<String?, Any?>) {
                            if (entry.key == null || entry.value == null) {
                                continue
                            }
                            this.header(entry.key.toString(), StringSerializer.serialize(entry.value!!))
                        }
                    }
                    this.header("content-type", "application/json")
                    this.body = JsonSerializer.serialize(payload)
                }
                // trigger messageReceived
                super.trigger(MessageReceivedEvent(response.receive(), payload["requestId"] as String?, response.headers.toMap()))
            } catch (e: Exception) {
                super.trigger(RequestErrorEvent(e, payload["requestId"] as String?))
            } finally {
                client.close()
            }
        }
    }

    /**
     * Make a request to Kuzzle using the appropriate HTTP Endpoint for a given controller's action
     */
    private fun queryHTTPEndpoint(payload: Map<String?, Any?>, requestInfo: io.kuzzle.sdk.coreClasses.http.HttpRequest) {
        GlobalScope.launch { // Launch HTTP Request inside a coroutine to be non-blocking
            val client = HttpClient() {
                expectSuccess = false
            }
            try {
                val response: HttpResponse = client.request("$uri${requestInfo.url}") {
                    this.method = HttpMethod.parse(requestInfo.verb)
                    for (entry in requestInfo.headers) {
                        if (entry.key == null || entry.value == null) {
                            continue
                        }

                        this.header(entry.key.toString(), StringSerializer.serialize(entry.value!!))
                    }
                    this.header("content-type", "application/json")
                    this.body = if (requestInfo.body != null) JsonSerializer.serialize(requestInfo.body) else ""
                }
                // trigger messageReceived
                super.trigger(MessageReceivedEvent(response.receive(), payload["requestId"] as String?, response.headers.toMap()))
            } catch (e: Exception) {
                super.trigger(RequestErrorEvent(e, payload["requestId"] as String?))
            } finally {
                client.close()
            }
        }
    }

    override fun send(payload: KuzzleMap) {
        if (! payload.containsKey("controller") || payload["controller"] !is String) {
            super.trigger(RequestErrorEvent(MissingControllerException(), payload["requestId"] as String?))
            return
        }

        if (! payload.containsKey("action") || payload["action"] !is String) {
            super.trigger(RequestErrorEvent(MissingActionException(), payload["requestId"] as String?))
            return
        }

        if (payload["headers"] == null) {
            payload["headers"] = KuzzleMap()
        }

        var headers = payload.getMap("headers")!!

        if (payload["jwt"] != null) {
            headers["Authorization"] = "Bearer ${payload["jwt"]}"
        }
        if (payload["volatile"] != null) {
            headers["x-kuzzle-volatile"] = StringSerializer.serialize(payload["volatile"]!!)
        }
        if (payload["requestId"] != null) {
            headers["x-kuzzle-request-id"] = payload["requestId"].toString()
        }

        /**
         * If no route has been built we try to send the request to Kuzzle using
         * the /_query endpoint
         */
        if (! useBuiltRoutes) {
            query(payload)
            return
        }

        val controller = payload["controller"] as String
        val action = payload["action"] as String

        val route = this.routes["$controller:$action"]

        if (route != null) {
            try {
                val requestInfo = route.buildRequest(KuzzleMap.from(payload))
                queryHTTPEndpoint(payload, requestInfo)
                return
            } catch (e: MissingURLParamException) {
                // Fallback to query
            }
        }
        /**
         * Fallback if we could not find a matching route with the given parameters
         * Try to make the request using directly using /_query endpoint
         */
        query(payload)
    }
}
