package io.kuzzle.sdk.controllers

import io.kuzzle.sdk.Kuzzle
import io.kuzzle.sdk.coreClasses.maps.KuzzleMap
import io.kuzzle.sdk.coreClasses.responses.Response
import io.kuzzle.sdk.coreClasses.SearchResult
import io.kuzzle.sdk.coreClasses.lang.Lang
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap

class AuthController(kuzzle: Kuzzle) : BaseController(kuzzle) {

  fun checkRights(
      requestPayload: ConcurrentHashMap<String, Any?>): CompletableFuture<Boolean> {
    val query = KuzzleMap().apply {
      put("controller", "auth")
      put("action", "checkRights")
      put("body", requestPayload)
    }
    return kuzzle
        .query(query)
        .thenApplyAsync { response -> KuzzleMap
          .from(response.result as ConcurrentHashMap<String?, Any?>)
          .getBoolean("allowed") as Boolean
          }
  }

  fun checkToken(
      token: String): CompletableFuture<ConcurrentHashMap<String, Any?>> {
    val query = KuzzleMap().apply {
      put("controller", "auth")
      put("action", "checkToken")
      put("body", KuzzleMap().apply {
        put("token", token)
      })
    }
    return kuzzle
        .query(query)
        .thenApplyAsync { response -> response.result as ConcurrentHashMap<String, Any?> }
  }

  fun createMyCredentials(
      strategy: String,
      credentials: ConcurrentHashMap<String, Any?>): CompletableFuture<ConcurrentHashMap<String, Any?>> {
    val query = KuzzleMap().apply {
      put("controller", "auth")
      put("action", "createMyCredentials")
      put("body", credentials)
      put("strategy", strategy)
    }
    return kuzzle
        .query(query)
        .thenApplyAsync { response -> response.result as ConcurrentHashMap<String, Any?> }
  }

  fun credentialsExist(strategy: String): CompletableFuture<Boolean> {
    val query = KuzzleMap().apply {
      put("controller", "auth")
      put("action", "credentialsExist")
      put("strategy", strategy)
    }
    return kuzzle
        .query(query)
        .thenApplyAsync { response -> response.result as Boolean? }
  }

  fun deleteMyCredentials(strategy: String): CompletableFuture<Void> {
    val query = KuzzleMap().apply {
      put("controller", "auth")
      put("action", "deleteMyCredentials")
      put("strategy", strategy)
    }
    return kuzzle.query(query).thenRunAsync {}
  }

  fun getCurrentUser(): CompletableFuture<ConcurrentHashMap<String, Any?>> {
    val query = KuzzleMap().apply {
      put("controller", "auth")
      put("action", "getCurrentUser")
    }
    return kuzzle
        .query(query)
        .thenApplyAsync { response ->
          response.result as ConcurrentHashMap<String, Any?>
        }
  }

  fun getMyCredentials(
      strategy: String): CompletableFuture<ConcurrentHashMap<String, Any?>> {
    val query = KuzzleMap().apply {
      put("controller", "auth")
      put("action", "getMyCredentials")
      put("strategy", strategy)
    }
    return kuzzle
        .query(query)
        .thenApplyAsync { response ->
          response.result as ConcurrentHashMap<String, Any?>
        }
  }

  fun getMyRights(): CompletableFuture<ArrayList<Any>> {
    val query = KuzzleMap().apply {
      put("controller", "auth")
      put("action", "getMyRights")
    }
    return kuzzle
        .query(query)
        .thenApplyAsync { response ->
          KuzzleMap
              .from(response.result as ConcurrentHashMap<String?, Any?>)
              .getArrayList("hits") as ArrayList<Any>
        }
  }

  fun getStrategies(): CompletableFuture<ArrayList<String>> {
    val query = KuzzleMap().apply {
      put("controller", "auth")
      put("action", "getStrategies")
    }
    return kuzzle
        .query(query)
        .thenApplyAsync { response -> response.result as ArrayList<String> }
  }

  @JvmOverloads
  fun login(
      strategy: String,
      credentials: ConcurrentHashMap<String, Any?>?,
      expiresIn: String? = null): CompletableFuture<ConcurrentHashMap<String, Any?>> {
    val query = KuzzleMap().apply {
      put("controller", "auth")
      put("action", "login")
      put("strategy", strategy)
      put("body", credentials)
    }
    if (expiresIn != null) {
      query["expiresIn"] = expiresIn
    }
    return kuzzle.query(query).thenApplyAsync { response ->
      val map: KuzzleMap = KuzzleMap
          .from(response.result as ConcurrentHashMap<String?, Any?>)
      kuzzle.authenticationToken = map.getString("jwt")
      if (map.getString("_id") != null) {
        kuzzle.protocol.trigger("loginAttempt", "true")
      } else {
        kuzzle.protocol.trigger("loginAttempt", "false")
      }
      map as ConcurrentHashMap<String, Any?>
    }
  }

  fun logout(): CompletableFuture<Response> {
    val query = KuzzleMap().apply {
      put("controller", "auth")
      put("action", "logout")
    }
    return kuzzle.query(query)
  }

  @JvmOverloads
  fun refreshToken(
      expiresIn: String? = null): CompletableFuture<ConcurrentHashMap<String, Any?>> {
    val query = KuzzleMap().apply {
      put("controller", "auth")
      put("action", "refreshToken")
      put("expiresIn", expiresIn)
    }
    return kuzzle.query(query).thenApplyAsync { response ->
      val map: KuzzleMap = KuzzleMap
          .from(response.result as ConcurrentHashMap<String?, Any?>)
      kuzzle.authenticationToken = map.getString("jwt")
      map as ConcurrentHashMap<String, Any?>
    }
  }

  @JvmOverloads
  fun searchApiKeys(
      query: ConcurrentHashMap<String, Any?>,
      from: Int = 0,
      size: Int? = null,
      lang: Lang = Lang.ELASTICSEARCH): CompletableFuture<SearchResult> {
    val query = KuzzleMap().apply {
      put("controller", "auth")
      put("action", "searchApiKeys")
      put("body", query)
      put("from", from)
      put("size", size)
      put("lang", lang.lang)
    }
    return kuzzle
        .query(query)
        .thenApplyAsync { response -> SearchResult(kuzzle, query, null, from, size, lang.lang, response) }
  }

  fun searchApiKeys(
      query: ConcurrentHashMap<String, Any?>,
      lang: Lang = Lang.ELASTICSEARCH): CompletableFuture<SearchResult> {
    
    return searchApiKeys(query, 0, null, lang);
  }

  fun updateMyCredentials(
      strategy: String,
      credentials: ConcurrentHashMap<String, Any?>): CompletableFuture<ConcurrentHashMap<String, Any?>> {
    val query = KuzzleMap().apply {
      put("controller", "auth")
      put("action", "updateMyCredentials")
      put("strategy", strategy)
      put("body", credentials)
    }
    return kuzzle
        .query(query)
        .thenApplyAsync { response ->
          response.result as ConcurrentHashMap<String, Any?>
        }
  }

  fun updateSelf(
      content: ConcurrentHashMap<String, Any?>): CompletableFuture<ConcurrentHashMap<String, Any?>> {
    val query = KuzzleMap().apply {
      put("controller", "auth")
      put("action", "updateSelf")
      put("body", content)
    }
    return kuzzle
        .query(query)
        .thenApplyAsync { response ->
          response.result as ConcurrentHashMap<String, Any?>
        }
  }

  fun validateMyCredentials(strategy: String,
                            credentials: ConcurrentHashMap<String, Any?>): CompletableFuture<Boolean> {
    val query = KuzzleMap().apply {
      put("controller", "auth")
      put("action", "validateMyCredentials")
      put("strategy", strategy)
      put("body", credentials)
    }
    return kuzzle
        .query(query)
        .thenApplyAsync { response -> response.result as Boolean }
  }
}