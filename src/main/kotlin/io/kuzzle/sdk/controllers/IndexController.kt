package io.kuzzle.sdk.controllers

import io.kuzzle.sdk.Kuzzle
import io.kuzzle.sdk.coreClasses.maps.KuzzleMap
import java.util.concurrent.CompletableFuture

class IndexController(kuzzle: Kuzzle) : BaseController(kuzzle) {

    fun create(index: String): CompletableFuture<Void> {
        return kuzzle
            .query(
                KuzzleMap().apply {
                    put("controller", "index")
                    put("action", "create")
                    put("index", index)
                }
            )
            .thenApplyAsync { null }
    }

    fun delete(index: String): CompletableFuture<Void> {
        return kuzzle
            .query(
                KuzzleMap().apply {
                    put("controller", "index")
                    put("action", "delete")
                    put("index", index)
                }
            )
            .thenApplyAsync { null }
    }

    fun exists(index: String): CompletableFuture<Boolean> {
        return kuzzle
            .query(
                KuzzleMap().apply {
                    put("controller", "index")
                    put("action", "exists")
                    put("index", index)
                }
            )
            .thenApplyAsync { response -> response.result as Boolean }
    }

    fun list(): CompletableFuture<ArrayList<String>> {
        return kuzzle
            .query(
                KuzzleMap().apply {
                    put("controller", "index")
                    put("action", "list")
                }
            )
            .thenApplyAsync { response -> (response.result as KuzzleMap).getArrayList("indexes") as ArrayList<String> }
    }

    fun mDelete(indexes: ArrayList<String>): CompletableFuture<ArrayList<String>> {
        return kuzzle
            .query(
                KuzzleMap().apply {
                    put("controller", "index")
                    put("action", "mDelete")
                    put("body", KuzzleMap().put("indexes", indexes))
                }
            )
            .thenApplyAsync { response -> (response.result as KuzzleMap).getArrayList("deleted") as ArrayList<String> }
    }
}
