package io.kuzzle.sdk.coreClasses

import com.google.gson.internal.LazilyParsedNumber
import io.kuzzle.sdk.Kuzzle
import io.kuzzle.sdk.coreClasses.responses.Response
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ExecutionException
import java.util.function.Function

class SearchResult {
  private var scroll: String? = null
  private var from: Int = 0
  private var size: Int? = null
  private var request: ConcurrentHashMap<String?, Any?>? = null
  private val scrollAction = "scroll"
  private var kuzzle: Kuzzle? = null

  var aggregations: ConcurrentHashMap<String, Any>? = null
  var hits: ArrayList<ConcurrentHashMap<String, Any>>? = null
  var total: Int = 0
  var fetched: Int = 0
  var scrollId: String? = null

  @JvmOverloads
  constructor(
      kuzzle: Kuzzle?,
      request: ConcurrentHashMap<String?, Any?>?,
      scroll: String? = null,
      from: Int,
      size: Int? = null,
      response: Response,
      previouslyFetched: Int? = null) {
    val _response: ConcurrentHashMap<String?, Any?> = response.toMap()
    this.kuzzle = kuzzle
    this.scroll = scroll
    this.from = from
    this.size = size
    this.request = request
    aggregations = (_response["result"] as ConcurrentHashMap<String?, Any?>)["aggregations"] as ConcurrentHashMap<String, Any>?
    hits = (_response["result"] as ConcurrentHashMap<String?, Any?>)["hits"] as ArrayList<ConcurrentHashMap<String, Any>>?
    total = ((_response["result"] as ConcurrentHashMap<String?, Any?>)["total"] as LazilyParsedNumber?)!!.toInt()
    fetched = hits!!.size
    if (previouslyFetched != null) {
      fetched += previouslyFetched
    }
    scrollId = (_response["result"] as ConcurrentHashMap<String?, Any?>)["scrollId"] as String?
  }

  private fun getScrollRequest(): ConcurrentHashMap<String?, Any?>? {
    val obj = ConcurrentHashMap<String?, Any?>()
    obj["controller"] = request!!["controller"]!!
    obj["action"] = scrollAction
    obj["scrollId"] = scrollId!!
    return obj
  }

  private fun getSearchAfterRequest(): ConcurrentHashMap<String?, Any?>? {
    val nextRequest = request
    val lastItem = hits!![hits!!.size]
    val searchAfter = ArrayList<Any?>()
    val sort = (request!!["body"] as ConcurrentHashMap<String?, Any?>?)!!["sort"] as ArrayList<Any>?
    (request!!["body"] as ConcurrentHashMap<String?, Any?>?)!!["search_after"] = searchAfter
    for (value in sort!!) {
      var key: String = (value as? String)?.toString() ?: (value as ConcurrentHashMap<String?, Any>)["First"].toString()
      if (key == "_uid") {
        searchAfter.add(request!!["collection"].toString() + "#" + lastItem["_id"].toString())
      } else {
        val _source = lastItem["_source"] as ConcurrentHashMap<String, Any>?
        searchAfter.add(_source!![key])
      }
    }
    return nextRequest
  }

  operator fun next(): CompletableFuture<SearchResult> {
    if (fetched >= total) return CompletableFuture.completedFuture(null)
    var nextRequest: ConcurrentHashMap<String?, Any?>? = ConcurrentHashMap()
    if (scrollId != null) {
      nextRequest = getScrollRequest()
    } else if (size != null
        && (request!!["body"] as ConcurrentHashMap<String?, Any?>?)!!["sort"] != null) {
      nextRequest = getSearchAfterRequest()
    } else if (size != null) {
      if (from != null && from > total) {
        return CompletableFuture.completedFuture(null)
      }
      from = fetched
      nextRequest = request
    }
    if (nextRequest == null) {
      return CompletableFuture.completedFuture(null)
    }
    val request: ConcurrentHashMap<String?, Any?> = nextRequest
    return kuzzle!!.query(nextRequest)
        .thenApplyAsync(
            Function { response: Response -> SearchResult(kuzzle, request, scroll, from, size, response, fetched) })
  }
}