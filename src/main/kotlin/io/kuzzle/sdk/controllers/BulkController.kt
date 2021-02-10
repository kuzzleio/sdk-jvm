package io.kuzzle.sdk.controllers

import io.kuzzle.sdk.Kuzzle
import io.kuzzle.sdk.coreClasses.maps.KuzzleMap
import java.util.*
import java.util.concurrent.CompletableFuture

class BulkController(kuzzle: Kuzzle) : BaseController(kuzzle) {

    @JvmOverloads
    fun deleteByQuery(
        index: String,
        collection: String,
        searchQuery: Map<String, Any?>,
        waitForRefresh: Boolean? = null
    ): CompletableFuture<Int> {
        val query = KuzzleMap().apply {
            put("index", index)
            put("collection", collection)
            put("controller", "bulk")
            put("action", "deleteByQuery")
            put(
                "body",
                HashMap<String, Any?>().apply {
                    put("query", searchQuery)
                }
            )
            put("waitForRefresh", waitForRefresh)
        }
        return kuzzle
            .query(query)
            .thenApplyAsync { response -> (response.result as Map<String?, Any?>)["deleted"] as Int }
    }

    fun importData(
        index: String,
        collection: String,
        bulkData: ArrayList<Map<String, Any?>>
    ): CompletableFuture<Map<String, Any?>> {
        val query = KuzzleMap().apply {
            put("index", index)
            put("collection", collection)
            put("controller", "bulk")
            put("action", "import")
            put(
                "body",
                HashMap<String, Any?>().apply {
                    put("bulkData", bulkData)
                }
            )
        }
        return kuzzle
            .query(query)
            .thenApplyAsync { response -> (response.result as Map<String, Any?>) }
    }

    @JvmOverloads
    fun mWrite(
        index: String,
        collection: String,
        documents: ArrayList<Map<String, Any?>>,
        notify: Boolean? = null,
        waitForRefresh: Boolean? = null
    ): CompletableFuture<Map<String, Any?>> {
        val query = KuzzleMap().apply {
            put("index", index)
            put("collection", collection)
            put("controller", "bulk")
            put("action", "mWrite")
            put(
                "body",
                HashMap<String, Any?>().apply {
                    put("documents", documents)
                }
            )
            put("waitForRefresh", waitForRefresh)
            put("notify", notify)
        }
        return kuzzle
            .query(query)
            .thenApplyAsync { response -> (response.result as Map<String, Any?>) }
    }

    @JvmOverloads
    fun write(
        index: String,
        collection: String,
        content: Map<String, Any?>,
        id: String? = null,
        notify: Boolean? = null,
        waitForRefresh: Boolean? = null
    ): CompletableFuture<Map<String, Any?>> {
        val query = KuzzleMap().apply {
            put("index", index)
            put("collection", collection)
            put("controller", "bulk")
            put("action", "write")
            put("body", content)
            put("waitForRefresh", waitForRefresh)
            put("_id", id)
            put("notify", notify)
        }
        return kuzzle
            .query(query)
            .thenApplyAsync { response -> (response.result as Map<String, Any?>) }
    }
}
