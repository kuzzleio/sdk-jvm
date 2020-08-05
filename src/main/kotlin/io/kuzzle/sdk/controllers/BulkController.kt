package io.kuzzle.sdk.controllers

import io.kuzzle.sdk.Kuzzle
import io.kuzzle.sdk.coreClasses.maps.KuzzleMap
import io.kuzzle.sdk.coreClasses.responses.Response
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap
import com.google.gson.internal.LazilyParsedNumber

class BulkController(kuzzle: Kuzzle) : BaseController(kuzzle) {

@JvmOverloads
  fun deleteByQuery(
    index: String,
    collection: String,
    query: ConcurrentHashMap<String, Any?>,
    waitForRefresh: Boolean? = null): CompletableFuture<Int> {
    val query = KuzzleMap().apply {
      put("index", index)
      put("collection", collection)
      put("controller", "bulk")
      put("action", "deleteByQuery")
      put("body", ConcurrentHashMap<String, Any?>().apply {
        put("query", query)
      })
      put("waitForRefresh", waitForRefresh)
    }
    return kuzzle
        .query(query)
        .thenApplyAsync { response -> (response.result as ConcurrentHashMap<String?, Any?>)["deleted"] as Int}
        // .thenApplyAsync { response -> (response.result as KuzzleMap?)!!.getNumber("deleted")?.toInt() }
  }

  fun importData(
      index: String,
      collection: String,
      bulkData: ArrayList<ConcurrentHashMap<String, Any?>>): CompletableFuture<ConcurrentHashMap<String, Any?>> {
    val query = KuzzleMap().apply {
      put("index", index)
      put("collection", collection)
      put("controller", "bulk")
      put("action", "import")
      put("body", ConcurrentHashMap<String, Any?>().apply {
        put("bulkData", bulkData)
      })
    }
    return kuzzle
        .query(query)
        .thenApplyAsync { response -> (response.result as ConcurrentHashMap<String, Any?>) }
  }

@JvmOverloads
  fun mWrite(
      index: String,
      collection: String,
      documents: ArrayList<ConcurrentHashMap<String, Any?>>,
      notify: Boolean? = null,
      waitForRefresh: Boolean? = null): CompletableFuture<ConcurrentHashMap<String, Any?>> {
    val query = KuzzleMap().apply {
      put("index", index)
      put("collection", collection)
      put("controller", "bulk")
      put("action", "mWrite")
      put("body", ConcurrentHashMap<String, Any?>().apply {
        put("documents", documents)
      })
      put("waitForRefresh", waitForRefresh)
      put("notify", notify)
    }
    return kuzzle
        .query(query)
        .thenApplyAsync { response -> (response.result as ConcurrentHashMap<String, Any?>) }
  }

@JvmOverloads
  fun write(
      index: String,
      collection: String,
      content: ConcurrentHashMap<String, Any?>,
      id: String? = null,
      notify: Boolean? = null,
      waitForRefresh: Boolean? = null): CompletableFuture<ConcurrentHashMap<String, Any?>> {
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
        .thenApplyAsync { response -> (response.result as ConcurrentHashMap<String, Any?>) }
  }
}