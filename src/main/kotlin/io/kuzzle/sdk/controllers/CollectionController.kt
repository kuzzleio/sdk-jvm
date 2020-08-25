package io.kuzzle.sdk.controllers

import io.kuzzle.sdk.Kuzzle
import io.kuzzle.sdk.coreClasses.maps.KuzzleMap
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap

class CollectionController(kuzzle: Kuzzle) : BaseController(kuzzle) {

  fun create(
      index: String,
      collection: String,
      mapping: ConcurrentHashMap<String, Any?>? = null,
      settings: ConcurrentHashMap<String, Any?>? = null,
    ): CompletableFuture<Boolean> {
    return kuzzle
      .query(KuzzleMap().apply {
        put("controller", "collection")
        put("action", "create")
        put("index", index)
        put("collection", collection)
        put("mapping", mapping)
        put("settings", settings)
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

  fun deleteSpecification(
      index: String,
      collection: String
    ): CompletableFuture<Void> {
    return kuzzle
      .query(KuzzleMap().apply {
        put("controller", "collection")
        put("action", "deleteSpecification")
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

  fun list(): CompletableFuture<ArrayList<String>> {
    return kuzzle
        .query(KuzzleMap().apply {
          put("controller", "collection")
          put("action", "list")
        })
        .thenApplyAsync { response -> (response.result as KuzzleMap).getArrayList("collections") as ArrayList<String> }
  }








  fun refresh(
    index: String,
    collection: String
  ): CompletableFuture<Void> {
    return kuzzle
        .query(KuzzleMap().apply {
          put("controller", "collection")
          put("action", "refresh"),
          put("index", index)
          put("collection", collection)
        })
        .thenApplyAsync { null }
  }
  fun searchSpecifications(): CompletableFuture<ArrayList<String>> {
    return kuzzle
        .query(KuzzleMap().apply {
          put("controller", "collection")
          put("action", "searchSpecifications")
        })
        .thenApplyAsync { response -> (response.result as KuzzleMap).getArrayList("indexes") as ArrayList<String> }
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
    collection: String
  ): CompletableFuture<ArrayList<String>> {
    return kuzzle
        .query(KuzzleMap().apply {
          put("controller", "collection")
          put("action", "update")
          put("index", index)
          put("collection", collection)
        })
        .thenApplyAsync { response -> (response.result as KuzzleMap).getArrayList("indexes") as ArrayList<String> }
  }
  fun updateMapping(
    index: String,
    collection: String
  ): CompletableFuture<ArrayList<String>> {
    return kuzzle
        .query(KuzzleMap().apply {
          put("controller", "collection")
          put("action", "updateMapping")
          put("index", index)
          put("collection", collection)
        })
        .thenApplyAsync { response -> (response.result as KuzzleMap).getArrayList("indexes") as ArrayList<String> }
  }
  fun updateSpecifications(
    index: String,
    collection: String
  ): CompletableFuture<ConcurrentHashMap<String, Any?>> {
    return kuzzle
        .query(KuzzleMap().apply {
          put("controller", "collection")
          put("action", "updateSpecifications")
          put("index", index)
          put("collection", collection)
        })
        .thenApplyAsync { response -> response.result as ConcurrentHashMap<String, Any?> }
  }
  fun validateSpecifications(
    index: String,
    collection: String
  ): CompletableFuture<ConcurrentHashMap<String, Any?>> {
    return kuzzle
        .query(KuzzleMap().apply {
          put("controller", "collection")
          put("action", "validateSpecifications")
          put("index", index)
          put("collection", collection)
        })
        .thenApplyAsync { response -> response.result as ConcurrentHashMap<String, Any?> }
  }
}