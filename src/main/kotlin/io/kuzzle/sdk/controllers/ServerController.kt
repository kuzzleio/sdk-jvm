package io.kuzzle.sdk.controllers

import io.kuzzle.sdk.Kuzzle
import io.kuzzle.sdk.coreClasses.maps.KuzzleMap
import java.util.Date
import java.util.concurrent.CompletableFuture

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
                    .from(response.result as Map<String?, Any?>)
                    .getBoolean("exists")
            }
    }

    fun getAllStats(): CompletableFuture<Map<String, Any?>> {
        val query = KuzzleMap().apply {
            put("controller", "server")
            put("action", "getAllStats")
        }
        return kuzzle
            .query(query)
            .thenApplyAsync { response ->
                response.result as Map<String, Any?>
            }
    }

    fun getConfig(): CompletableFuture<Map<String, Any?>> {
        val query = KuzzleMap().apply {
            put("controller", "server")
            put("action", "getConfig")
        }
        return kuzzle
            .query(query)
            .thenApplyAsync { response ->
                response.result as Map<String, Any?>
            }
    }

    fun getLastStats(): CompletableFuture<Map<String, Any?>> {
        val query = KuzzleMap().apply {
            put("controller", "server")
            put("action", "getLastStats")
        }
        return kuzzle
            .query(query)
            .thenApplyAsync { response ->
                response.result as Map<String, Any?>
            }
    }

    fun getStats(startTime: Date, stopTime: Date): CompletableFuture<Map<String, Any?>> {
        val query = KuzzleMap().apply {
            put("controller", "server")
            put("action", "getStats")
            put("startTime", startTime.time)
            put("stopTime", stopTime.time)
        }
        return kuzzle
            .query(query)
            .thenApplyAsync { response ->
                response.result as Map<String, Any?>
            }
    }

    fun info(): CompletableFuture<Map<String, Any?>> {
        val query = KuzzleMap().apply {
            put("controller", "server")
            put("action", "info")
        }
        return kuzzle
            .query(query)
            .thenApplyAsync { response ->
                response.result as Map<String, Any?>
            }
    }

    fun now(): CompletableFuture<Date> {

        return kuzzle
            .query(
                KuzzleMap().apply {
                    put("controller", "server")
                    put("action", "now")
                }
            )
            .thenApplyAsync { response ->
                val date = ((response.result as Map<String, Any>)["now"]).toString().toLong()
                Date(date)
            }
    }
}
