package io.kuzzle.sdk.coreClasses

import com.google.gson.internal.LazilyParsedNumber
import io.kuzzle.sdk.Kuzzle
import io.kuzzle.sdk.coreClasses.maps.KuzzleMap
import io.kuzzle.sdk.coreClasses.responses.Response
import java.util.concurrent.CompletableFuture
import java.util.function.Function

class SearchResult {
    private var scroll: String? = null
    private var from: Int = 0
    private var size: Int? = null
    private var lang: String? = "elasticsearch"
    private var request: Map<String?, Any?>? = null
    private val scrollAction = "scroll"
    private var kuzzle: Kuzzle? = null

    var aggregations: Map<String, Any>? = null
    @JvmField
    public var hits: ArrayList<Map<String, Any>> = ArrayList<Map<String, Any>>()
    @JvmField
    public var total: Int = 0
    @JvmField
    public var fetched: Int = 0
    var scrollId: String? = null

    @JvmOverloads
    constructor(
        kuzzle: Kuzzle?,
        request: Map<String?, Any?>?,
        scroll: String? = null,
        from: Int = 0,
        size: Int? = null,
        lang: String? = null,
        response: Response,
        previouslyFetched: Int? = null
    ) {
        val _response: Map<String?, Any?> = response.toMap()
        this.kuzzle = kuzzle
        this.scroll = scroll
        this.from = from
        this.size = size
        this.lang = lang
        this.request = request
        this.aggregations = (_response["result"] as Map<*, *>)["aggregations"] as Map<String, Any>?
        this.hits = (_response["result"] as Map<*, *>)["hits"] as ArrayList<Map<String, Any>>
        this.total = ((_response["result"] as Map<*, *>)["total"] as LazilyParsedNumber?)!!.toInt()
        this.fetched = hits!!.size
        if (previouslyFetched != null) {
            this.fetched += previouslyFetched
        }
        scrollId = (_response["result"] as Map<*, *>)["scrollId"] as String?
    }

    private fun getScrollRequest(): Map<String?, Any?>? {
        val obj = HashMap<String?, Any?>()
        obj["controller"] = request!!["controller"]!!
        obj["action"] = scrollAction
        obj["scrollId"] = scrollId!!
        return obj
    }

    private fun getSearchAfterRequest(): Map<String?, Any?>? {
        val nextRequest = request
        val lastItem = hits!![hits!!.size]
        val searchAfter = ArrayList<Any?>()
        val sort = (request!!["body"] as Map<*, *>)["sort"] as ArrayList<Any>?
        (request!!["body"] as HashMap<String?, Any?>)["search_after"] = searchAfter
        for (value in sort!!) {
            var key: String = (value as? String)?.toString() ?: (value as Map<*, *>)["First"].toString()
            if (key == "_uid") {
                searchAfter.add(request!!["collection"].toString() + "#" + lastItem["_id"].toString())
            } else {
                val _source = lastItem["_source"] as Map<*, *>?
                searchAfter.add(_source!![key])
            }
        }
        return nextRequest
    }

    operator fun next(): CompletableFuture<SearchResult?> {
        if (fetched >= total) return CompletableFuture.completedFuture(null)
        var nextRequest: Map<String?, Any?>? = null
        if (scrollId != null) {
            nextRequest = getScrollRequest()
        } else if (size != null &&
            (request!!["body"] as Map<*, *>)["sort"] != null
        ) {
            nextRequest = getSearchAfterRequest()
        } else if (size != null) {
            if (from != null && from >= total) {
                return CompletableFuture.completedFuture(null)
            }
            from = fetched
            nextRequest = request
        }
        if (nextRequest == null) {
            return CompletableFuture.completedFuture(null)
        }
        val request: Map<String?, Any?> = nextRequest
        return kuzzle!!.query(nextRequest)
            .thenApplyAsync(
                Function { response: Response -> SearchResult(kuzzle, KuzzleMap.from(request), scroll, from, size, lang, response, fetched) }
            )
    }
}
