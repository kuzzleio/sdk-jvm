package io.kuzzle.sdk.controllers

import io.kuzzle.sdk.Kuzzle
import io.kuzzle.sdk.coreClasses.maps.KuzzleMap
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap

class ServerController(kuzzle: Kuzzle) : BaseController(kuzzle) {

  fun adminExists(): CompletableFuture<Boolean> {
    val query = KuzzleMap().apply {
      put("controller", "server")
      put("action", "adminExists")
    }

    return kuzzle
        .query(query)
        .thenApplyAsync { response ->
          KuzzleMap
              .from(response.result as ConcurrentHashMap<String?, Any?>)
              .getBoolean("exists")
        }
  }

  fun getAllStats(): CompletableFuture<ConcurrentHashMap<String, Any?>> {
    val query = KuzzleMap().apply {
      put("controller", "server")
      put("action", "getAllStats")
    }
    return kuzzle
        .query(query)
        .thenApplyAsync { response ->
          response.result as ConcurrentHashMap<String, Any?>
        }
  }

  fun getConfig(): CompletableFuture<ConcurrentHashMap<String, Any?>> {
    val query = KuzzleMap().apply {
      put("controller", "server")
      put("action", "getConfig")
    }
    return kuzzle
        .query(query)
        .thenApplyAsync { response ->
          response.result as ConcurrentHashMap<String, Any?>
        }
  }

  fun getLastStats(): CompletableFuture<ConcurrentHashMap<String, Any?>> {
    val query = KuzzleMap().apply {
      put("controller", "server")
      put("action", "getLastStats")
    }
    return kuzzle
        .query(query)
        .thenApplyAsync { response ->
          response.result as ConcurrentHashMap<String, Any?>
        }
  }
}