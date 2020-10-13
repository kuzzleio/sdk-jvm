package io.kuzzle.sdk.controllers

import io.kuzzle.sdk.Kuzzle
import io.kuzzle.sdk.coreClasses.maps.KuzzleMap
import io.kuzzle.sdk.coreClasses.SearchResult
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap

class CollectionController(kuzzle: Kuzzle) : BaseController(kuzzle) {

  fun create(
      index: String,
      collection: String,
      mapping: ConcurrentHashMap<String, Any>?
    ): CompletableFuture<Boolean> {
    return kuzzle
      .query(KuzzleMap().apply {
        put("controller", "collection")
        put("action", "create")
        put("index", index)
        put("collection", collection)
        put("mapping", mapping)
      })
      .thenApplyAsync { response -> (response.result as ConcurrentHashMap<String, Any?>)["acknowledged"] as Boolean}
  }

  fun delete(
      index: String,
      collection: String
    ): CompletableFuture<Void> {
    return kuzzle
      .query(KuzzleMap().apply {
        put("controller", "collection")
        put("action", "delete")
        put("index", index)
        put("collection", collection)
      })
      .thenApplyAsync { null }
  }

  fun deleteSpecifications(
      index: String,
      collection: String
    ): CompletableFuture<Void> {
    return kuzzle
      .query(KuzzleMap().apply {
        put("controller", "collection")
        put("action", "deleteSpecifications")
        put("index", index)
        put("collection", collection)
      })
      .thenApplyAsync { null }
  }

  fun exists(
      index: String,
      collection: String
    ): CompletableFuture<Boolean> {
    return kuzzle
      .query(KuzzleMap().apply {
        put("controller", "collection")
        put("action", "exists")
        put("index", index)
        put("collection", collection)
      })
      .thenApplyAsync { response -> response.result as Boolean }
  }

  fun getMapping(
    index: String,
    collection: String
  ): CompletableFuture<ConcurrentHashMap<String, Any?>> {
  return kuzzle
    .query(KuzzleMap().apply {
      put("controller", "collection")
      put("action", "getMapping")
      put("index", index)
      put("collection", collection)
    })
    .thenApplyAsync { response -> response.result as ConcurrentHashMap<String, Any?> }
}

fun getSpecifications(
    index: String,
    collection: String
  ): CompletableFuture<ConcurrentHashMap<String, Any?>> {
  return kuzzle
    .query(KuzzleMap().apply {
      put("controller", "collection")
      put("action", "getSpecifications")
      put("index", index)
      put("collection", collection)
    })
    .thenApplyAsync { response -> response.result as ConcurrentHashMap<String, Any?> }
}

  fun list(index: String): CompletableFuture<ConcurrentHashMap<String, Any?>> {
    return kuzzle
        .query(KuzzleMap().apply {
          put("controller", "collection")
          put("action", "list")
          put("index", index)
        })
        .thenApplyAsync { response -> response.result as ConcurrentHashMap<String, Any?> }
  }

  fun refresh(
    index: String,
    collection: String
   ): CompletableFuture<Void> {
    return kuzzle
        .query(KuzzleMap().apply {
          put("controller", "collection")
          put("action", "refresh")
          put("index", index)
          put("collection", collection)
        })
        .thenApplyAsync { null }
  }

  @JvmOverloads
  fun searchSpecifications(
    searchQuery: ConcurrentHashMap<String, Any?>,
    scroll: String? = null,
    from: Int = 0,
    size: Int? = null
  ): CompletableFuture<SearchResult> {
    val query = KuzzleMap().apply {
          put("controller", "collection")
          put("action", "searchSpecifications")
          put("body", searchQuery)
          put("from", from)
          put("size", size)
          put("scroll", scroll)
        }
    return kuzzle
      .query(query)
      .thenApplyAsync { response -> SearchResult(kuzzle, query, scroll, from, size, response) }
  }

  fun truncate(
    index: String,
    collection: String
  ): CompletableFuture<Void> {
    return kuzzle
        .query(KuzzleMap().apply {
          put("controller", "collection")
          put("action", "truncate")
          put("index", index)
          put("collection", collection)
        })
        .thenApplyAsync { null }
  }
  
  fun update(
    index: String,
    collection: String,
    definition: ConcurrentHashMap<String, Any?>
  ): CompletableFuture<Void> {
    return kuzzle
        .query(KuzzleMap().apply {
          put("controller", "collection")
          put("action", "update")
          put("index", index)
          put("collection", collection)
          put("body", definition)
        })
        .thenApplyAsync { null }
  }

  fun updateSpecifications(
    index: String,
    collection: String,
    definition: ConcurrentHashMap<String, Any?>
  ): CompletableFuture<ConcurrentHashMap<String, Any?>> {
    return kuzzle
        .query(KuzzleMap().apply {
          put("controller", "collection")
          put("action", "updateSpecifications")
          put("index", index)
          put("collection", collection)
          put("body", definition)
        })
        .thenApplyAsync { response -> response.result as ConcurrentHashMap<String, Any?> }
  }

  fun validateSpecifications(
    index: String,
    collection: String,
    specifications: ConcurrentHashMap<String, Any>?
  ): CompletableFuture<ConcurrentHashMap<String, Any?>> {
    return kuzzle
        .query(KuzzleMap().apply {
          put("controller", "collection")
          put("action", "validateSpecifications")
          put("index", index)
          put("collection", collection)
          put("body", specifications)
        })
        .thenApplyAsync { response -> response.result as ConcurrentHashMap<String, Any?> }
  }
}