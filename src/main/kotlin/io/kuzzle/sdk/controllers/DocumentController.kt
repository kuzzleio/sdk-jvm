package io.kuzzle.sdk.controllers

import com.google.gson.internal.LazilyParsedNumber
import io.kuzzle.sdk.Kuzzle
import io.kuzzle.sdk.coreClasses.SearchResult
import io.kuzzle.sdk.coreClasses.maps.KuzzleMap
import java.util.concurrent.CompletableFuture

class DocumentController(kuzzle: Kuzzle) : BaseController(kuzzle) {

    @JvmOverloads
    fun count(
        index: String,
        collection: String,
        searchQuery: Map<String, Any?> = mapOf()
    ): CompletableFuture<Int> {
        val query = KuzzleMap().apply {
            put("index", index)
            put("collection", collection)
            put("controller", "document")
            put("body", KuzzleMap().put("query", searchQuery))
            put("action", "count")
        }
        return kuzzle
            .query(query)
            .thenApplyAsync { response -> ((response.result as Map<String, Any?>)["count"] as LazilyParsedNumber).toInt() }
    }

    @JvmOverloads
    fun create(
        index: String,
        collection: String,
        document: Map<String, Any?>,
        id: String? = null,
        waitForRefresh: Boolean? = null
    ): CompletableFuture<Map<String, Any?>> {
        val query = KuzzleMap().apply {
            put("index", index)
            put("collection", collection)
            put("controller", "document")
            put("action", "create")
            put("body", document)
            put("_id", id)
            put("waitForRefresh", waitForRefresh)
        }
        return kuzzle
            .query(query)
            .thenApplyAsync { response -> response.result as Map<String, Any?> }
    }

    @JvmOverloads
    fun createOrReplace(
        index: String,
        collection: String,
        id: String,
        document: Map<String, Any?>,
        waitForRefresh: Boolean? = null
    ): CompletableFuture<Map<String, Any?>> {
        val query = KuzzleMap().apply {
            put("index", index)
            put("collection", collection)
            put("controller", "document")
            put("action", "createOrReplace")
            put("body", document)
            put("_id", id)
            put("waitForRefresh", waitForRefresh)
        }
        return kuzzle
            .query(query)
            .thenApplyAsync { response -> response.result as Map<String, Any?> }
    }

    @JvmOverloads
    fun delete(
        index: String,
        collection: String,
        id: String?,
        waitForRefresh: Boolean? = null
    ): CompletableFuture<Map<String, Any?>> {
        val query = KuzzleMap().apply {
            put("index", index)
            put("collection", collection)
            put("controller", "document")
            put("action", "delete")
            put("_id", id)
            put("waitForRefresh", waitForRefresh)
        }

        return kuzzle
            .query(query)
            .thenApplyAsync { response -> response.result as Map<String, Any?> }
    }

    @JvmOverloads
    fun deleteByQuery(
        index: String,
        collection: String,
        searchQuery: Map<String, Any?>,
        waitForRefresh: Boolean? = null
    ): CompletableFuture<ArrayList<String>> {
        val query = KuzzleMap().apply {
            put("index", index)
            put("collection", collection)
            put("controller", "document")
            put("action", "deleteByQuery")
            put("body", searchQuery)
            put("waitForRefresh", waitForRefresh)
        }
        return kuzzle
            .query(query)
            .thenApplyAsync { response -> (response.result as Map<String?, Any?>)["ids"] as ArrayList<String> }
    }

    fun exists(
        index: String,
        collection: String,
        id: String
    ): CompletableFuture<Boolean> {
        val query = KuzzleMap().apply {
            put("index", index)
            put("collection", collection)
            put("controller", "document")
            put("action", "exists")
            put("_id", id)
        }
        return kuzzle
            .query(query)
            .thenApplyAsync { response -> response.result as Boolean }
    }

    fun get(
        index: String,
        collection: String,
        id: String
    ): CompletableFuture<Map<String, Any?>> {
        val query = KuzzleMap().apply {
            put("index", index)
            put("collection", collection)
            put("controller", "document")
            put("action", "get")
            put("_id", id)
        }
        return kuzzle
            .query(query)
            .thenApplyAsync { response -> response.result as Map<String, Any?> }
    }

    @JvmOverloads
    fun mCreate(
        index: String,
        collection: String,
        documents: ArrayList<Map<String, Any?>>,
        waitForRefresh: Boolean? = null
    ): CompletableFuture<Map<String, ArrayList<Any>>> {
        val query = KuzzleMap().apply {
            put("index", index)
            put("collection", collection)
            put("controller", "document")
            put("action", "mCreate")
            put("body", KuzzleMap().put("documents", documents))
            put("waitForRefresh", waitForRefresh)
        }

        return kuzzle
            .query(query)
            .thenApplyAsync { response -> response.result as Map<String, ArrayList<Any>> }
    }

    @JvmOverloads
    fun mCreateOrReplace(
        index: String,
        collection: String,
        documents: ArrayList<Map<String, Any?>>,
        waitForRefresh: Boolean? = null
    ): CompletableFuture<Map<String, ArrayList<Any>>> {
        val query = KuzzleMap().apply {
            put("index", index)
            put("collection", collection)
            put("controller", "document")
            put("action", "mCreateOrReplace")
            put("body", KuzzleMap().put("documents", documents))
            put("waitForRefresh", waitForRefresh)
        }
        return kuzzle
            .query(query)
            .thenApplyAsync { response -> response.result as Map<String, ArrayList<Any>> }
    }

    @JvmOverloads
    fun mDelete(
        index: String,
        collection: String,
        ids: ArrayList<String>,
        waitForRefresh: Boolean? = null
    ): CompletableFuture<Map<String, ArrayList<Any>>> {
        val query = KuzzleMap().apply {
            put("index", index)
            put("collection", collection)
            put("controller", "document")
            put("action", "mDelete")
            put("waitForRefresh", waitForRefresh)
            put("body", KuzzleMap().put("ids", ids))
        }

        return kuzzle
            .query(query)
            .thenApplyAsync { response -> response.result as Map<String, ArrayList<Any>> }
    }

    fun mGet(
        index: String,
        collection: String,
        ids: ArrayList<String>
    ): CompletableFuture<Map<String, ArrayList<Any>>> {
        val query = KuzzleMap().apply {
            put("index", index)
            put("collection", collection)
            put("controller", "document")
            put("action", "mGet")
            put("body", KuzzleMap().put("ids", ids))
        }

        return kuzzle
            .query(query)
            .thenApplyAsync { response -> response.result as Map<String, ArrayList<Any>> }
    }

    @JvmOverloads
    fun mReplace(
        index: String,
        collection: String,
        documents: ArrayList<Map<String, Any?>>,
        waitForRefresh: Boolean? = null
    ): CompletableFuture<Map<String, ArrayList<Any>?>> {
        val query = KuzzleMap().apply {
            put("index", index)
            put("collection", collection)
            put("controller", "document")
            put("action", "mReplace")
            put("body", KuzzleMap().put("documents", documents))
            put("waitForRefresh", waitForRefresh)
        }
        return kuzzle
            .query(query)
            .thenApplyAsync { response -> response.result as Map<String, ArrayList<Any>?> }
    }

    @JvmOverloads
    fun mUpdate(
        index: String,
        collection: String,
        documents: ArrayList<Map<String, Any?>?>,
        waitForRefresh: Boolean? = null,
        retryOnConflict: Int? = null
    ): CompletableFuture<Map<String, ArrayList<Any>?>> {
        val query = KuzzleMap().apply {
            put("index", index)
            put("collection", collection)
            put("controller", "document")
            put("action", "mUpdate")
            put("body", KuzzleMap().put("documents", documents))
            put("retryOnConflict", retryOnConflict)
            put("waitForRefresh", waitForRefresh)
        }
        return kuzzle
            .query(query)
            .thenApplyAsync { response -> response.result as Map<String, ArrayList<Any>?> }
    }

    @JvmOverloads
    fun replace(
        index: String,
        collection: String,
        id: String?,
        document: Map<String, Any?>,
        waitForRefresh: Boolean? = null
    ): CompletableFuture<Map<String, Any?>> {
        val query = KuzzleMap().apply {
            put("index", index)
            put("collection", collection)
            put("controller", "document")
            put("action", "replace")
            put("body", document)
            put("_id", id)
            put("waitForRefresh", waitForRefresh)
        }

        return kuzzle
            .query(query)
            .thenApplyAsync { response -> response.result as Map<String, Any?> }
    }

    @JvmOverloads
    fun search(
        index: String,
        collection: String,
        searchQuery: Map<String, Any?>,
        scroll: String? = null,
        size: Int? = null,
        from: Int = 0
    ): CompletableFuture<SearchResult> {
        val query = KuzzleMap().apply {
            put("index", index)
            put("collection", collection)
            put("controller", "document")
            put("action", "search")
            put("body", searchQuery)
            put("from", from)
            put("size", size)
            put("scroll", scroll)
        }
        if (scroll != null) {
            query["scroll"] = scroll
        }

        return kuzzle
            .query(query)
            .thenApplyAsync { response -> SearchResult(kuzzle, query, scroll, from, size, response) }
    }

    fun search(
        index: String,
        collection: String,
        searchQuery: Map<String, Any?>,
        size: Int? = null,
        from: Int = 0
    ): CompletableFuture<SearchResult> {

        return search(index, collection, searchQuery, null, size, from)
    }

    @JvmOverloads
    fun update(
        index: String,
        collection: String,
        id: String?,
        document: Map<String, Any?>,
        waitForRefresh: Boolean? = null,
        retryOnConflict: Int? = null,
        source: Boolean? = null
    ): CompletableFuture<Map<String, Any?>> {
        val query = KuzzleMap().apply {
            put("index", index)
            put("collection", collection)
            put("controller", "document")
            put("action", "update")
            put("body", document)
            put("_id", id)
            put("waitForRefresh", waitForRefresh)
            put("retryOnConflict", retryOnConflict)
            put("source", source)
        }
        return kuzzle
            .query(query)
            .thenApplyAsync { response -> response.result as Map<String, Any?> }
    }

    @JvmOverloads
    fun updateByQuery(
        index: String,
        collection: String,
        searchQuery: Map<String, Any?>,
        changes: Map<String, Any?>,
        waitForRefresh: Boolean? = null,
        retryOnConflict: Int? = null,
        source: Boolean? = null
    ): CompletableFuture<Map<String, ArrayList<Any?>>> {
        val query = KuzzleMap().apply {
            put("index", index)
            put("collection", collection)
            put("controller", "document")
            put("action", "updateByQuery")
            put(
                "body",
                KuzzleMap().apply {
                    put("query", searchQuery)
                    put("changes", changes)
                }
            )
            put("source", source)
            put("retryOnConflict", retryOnConflict)
            put("waitForRefresh", waitForRefresh)
        }

        return kuzzle
            .query(query)
            .thenApplyAsync { response -> response.result as Map<String, ArrayList<Any?>> }
    }

    fun validate(
        index: String,
        collection: String,
        document: Map<String, Any?>
    ): CompletableFuture<Boolean> {
        val query = KuzzleMap().apply {
            put("index", index)
            put("collection", collection)
            put("controller", "document")
            put("action", "validate")
            put("body", document)
        }
        return kuzzle
            .query(query)
            .thenApplyAsync { response -> (response.result as Map<*, *>)["valid"] as Boolean }
    }
}
