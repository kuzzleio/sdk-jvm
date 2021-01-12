package io.kuzzle.sdk.controllers

import com.google.gson.internal.LazilyParsedNumber
import io.kuzzle.sdk.Kuzzle
import io.kuzzle.sdk.coreClasses.SearchResult
import io.kuzzle.sdk.coreClasses.maps.KuzzleMap
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap

class DocumentController(kuzzle: Kuzzle) : BaseController(kuzzle) {

  @JvmOverloads
  fun count(
      index: String,
      collection: String,
      searchQuery: ConcurrentHashMap<String, Any?> = ConcurrentHashMap<String, Any?>()): CompletableFuture<Int> {
    val query = KuzzleMap().apply {
      put("index", index)
      put("collection", collection)
      put("controller", "document")
      put("body", KuzzleMap().put("query", searchQuery))
      put("action", "count")
    }
    return kuzzle
        .query(query)
        .thenApplyAsync { response -> ((response.result as ConcurrentHashMap<String, Any?>)["count"] as LazilyParsedNumber).toInt() }
  }

  @JvmOverloads
  fun create(
      index: String,
      collection: String,
      document: ConcurrentHashMap<String, Any?>,
      id: String? = null,
      waitForRefresh: Boolean? = null): CompletableFuture<ConcurrentHashMap<String, Any?>> {
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
        .thenApplyAsync { response -> response.result as ConcurrentHashMap<String, Any?> }
  }

  @JvmOverloads
  fun createOrReplace(
      index: String,
      collection: String,
      id: String,
      document: ConcurrentHashMap<String, Any?>,
      waitForRefresh: Boolean? = null): CompletableFuture<ConcurrentHashMap<String, Any?>> {
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
        .thenApplyAsync { response -> response.result as ConcurrentHashMap<String, Any?> }
  }

  @JvmOverloads
  fun delete(
      index: String,
      collection: String,
      id: String?,
      waitForRefresh: Boolean? = null): CompletableFuture<ConcurrentHashMap<String, Any?>> {
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
        .thenApplyAsync { response -> response.result as ConcurrentHashMap<String, Any?> }
  }

  @JvmOverloads
  fun deleteByQuery(
      index: String,
      collection: String,
      searchQuery: ConcurrentHashMap<String, Any?>,
      waitForRefresh: Boolean? = null): CompletableFuture<ArrayList<String>> {
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
        .thenApplyAsync { response -> (response.result as ConcurrentHashMap<String?, Any?>)["ids"] as ArrayList<String> }
  }

  fun exists(
      index: String,
      collection: String,
      id: String): CompletableFuture<Boolean> {
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
      id: String): CompletableFuture<ConcurrentHashMap<String, Any?>> {
    val query = KuzzleMap().apply {
      put("index", index)
      put("collection", collection)
      put("controller", "document")
      put("action", "get")
      put("_id", id)
    }
    return kuzzle
        .query(query)
        .thenApplyAsync { response -> response.result as ConcurrentHashMap<String, Any?> }
  }

  @JvmOverloads
  fun mCreate(
      index: String,
      collection: String,
      documents: ArrayList<ConcurrentHashMap<String, Any?>>,
      waitForRefresh: Boolean? = null): CompletableFuture<ConcurrentHashMap<String, ArrayList<Any>>> {
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
        .thenApplyAsync { response -> response.result as ConcurrentHashMap<String, ArrayList<Any>> }
  }

  @JvmOverloads
  fun mCreateOrReplace(
      index: String,
      collection: String,
      documents: ArrayList<ConcurrentHashMap<String, Any?>>,
      waitForRefresh: Boolean? = null): CompletableFuture<ConcurrentHashMap<String, ArrayList<Any>>> {
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
        .thenApplyAsync { response -> response.result as ConcurrentHashMap<String, ArrayList<Any>> }
  }

  @JvmOverloads
  fun mDelete(
      index: String,
      collection: String,
      ids: ArrayList<String>,
      waitForRefresh: Boolean? = null): CompletableFuture<ConcurrentHashMap<String, ArrayList<Any>>> {
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
        .thenApplyAsync { response -> response.result as ConcurrentHashMap<String, ArrayList<Any>> }
  }

  fun mGet(
      index: String,
      collection: String,
      ids: ArrayList<String>): CompletableFuture<ConcurrentHashMap<String, ArrayList<Any>>> {
    val query = KuzzleMap().apply {
      put("index", index)
      put("collection", collection)
      put("controller", "document")
      put("action", "mGet")
      put("body", KuzzleMap().put("ids", ids))
    }

    return kuzzle
        .query(query)
        .thenApplyAsync { response -> response.result as ConcurrentHashMap<String, ArrayList<Any>> }
  }

  @JvmOverloads
  fun mReplace(
      index: String,
      collection: String,
      documents: ArrayList<ConcurrentHashMap<String, Any?>>,
      waitForRefresh: Boolean? = null): CompletableFuture<ConcurrentHashMap<String, ArrayList<Any>?>> {
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
        .thenApplyAsync { response -> response.result as ConcurrentHashMap<String, ArrayList<Any>?> }
  }

  @JvmOverloads
  fun mUpdate(
      index: String,
      collection: String,
      documents: ArrayList<ConcurrentHashMap<String, Any?>?>,
      waitForRefresh: Boolean? = null,
      retryOnConflict: Int? = null): CompletableFuture<ConcurrentHashMap<String, ArrayList<Any>?>> {
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
        .thenApplyAsync { response -> response.result as ConcurrentHashMap<String, ArrayList<Any>?> }
  }

  @JvmOverloads
  fun replace(
      index: String,
      collection: String,
      id: String?,
      document: ConcurrentHashMap<String, Any?>,
      waitForRefresh: Boolean? = null): CompletableFuture<ConcurrentHashMap<String, Any?>> {
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
        .thenApplyAsync { response -> response.result as ConcurrentHashMap<String, Any?> }
  }

  @JvmOverloads
  fun search(
      index: String,
      collection: String,
      searchQuery: ConcurrentHashMap<String, Any?>,
      scroll: String? = null,
      size: Int? = null,
      from: Int = 0): CompletableFuture<SearchResult> {
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
      searchQuery: ConcurrentHashMap<String, Any?>,
      size: Int? = null,
      from: Int = 0): CompletableFuture<SearchResult> {

    return search(index, collection, searchQuery, null, size, from);
  }

  @JvmOverloads
  fun update(
      index: String,
      collection: String,
      id: String?,
      document: ConcurrentHashMap<String, Any?>,
      waitForRefresh: Boolean? = null,
      retryOnConflict: Int? = null,
      source: Boolean? = null): CompletableFuture<ConcurrentHashMap<String, Any?>> {
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
        .thenApplyAsync { response -> response.result as ConcurrentHashMap<String, Any?> }
  }

  @JvmOverloads
  fun upsert(
      index: String,
      collection: String,
      id: String,
      changes: ConcurrentHashMap<String, Any?>,
      defaults: ConcurrentHashMap<String, Any?>? = null,
      waitForRefresh: Boolean? = null,
      retryOnConflict: Int? = null,
      source: Boolean? = null): CompletableFuture<ConcurrentHashMap<String, Any?>> {
    val query = KuzzleMap().apply {
      put("index", index)
      put("collection", collection)
      put("controller", "document")
      put("action", "upsert")
      put("body", KuzzleMap().apply {
        put("changes", changes)
        put("defaults", defaults)
      })
      put("_id", id)
      put("source", source)
      put("retryOnConflict", retryOnConflict)
      put("waitForRefresh", waitForRefresh)
    }

    if (defaults != null) {

    }

    return kuzzle
        .query(query)
        .thenApplyAsync { response -> response.result as ConcurrentHashMap<String, Any?> }
  }

  @JvmOverloads
  fun updateByQuery(
      index: String,
      collection: String,
      searchQuery: ConcurrentHashMap<String, Any?>,
      changes: ConcurrentHashMap<String, Any?>,
      waitForRefresh: Boolean? = null,
      retryOnConflict: Int? = null,
      source: Boolean? = null): CompletableFuture<ConcurrentHashMap<String, ArrayList<Any?>>> {
    val query = KuzzleMap().apply {
      put("index", index)
      put("collection", collection)
      put("controller", "document")
      put("action", "updateByQuery")
      put("body", KuzzleMap().apply {
        put("query", searchQuery)
        put("changes", changes)
      })
      put("source", source)
      put("retryOnConflict", retryOnConflict)
      put("waitForRefresh", waitForRefresh)
    }

    return kuzzle
        .query(query)
        .thenApplyAsync { response -> response.result as ConcurrentHashMap<String, ArrayList<Any?>> }
  }

  fun validate(
      index: String,
      collection: String,
      document: ConcurrentHashMap<String, Any?>): CompletableFuture<Boolean> {
    val query = KuzzleMap().apply {
      put("index", index)
      put("collection", collection)
      put("controller", "document")
      put("action", "validate")
      put("body", document)
    }
    return kuzzle
        .query(query)
        .thenApplyAsync { response -> (response.result as ConcurrentHashMap<*, *>)["valid"] as Boolean }
  }
}