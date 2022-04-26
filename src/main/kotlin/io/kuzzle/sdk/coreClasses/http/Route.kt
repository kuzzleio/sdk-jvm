package io.kuzzle.sdk.coreClasses.http

import io.kuzzle.sdk.coreClasses.exceptions.MissingURLParamException
import io.kuzzle.sdk.coreClasses.maps.KuzzleMap
import java.net.URLEncoder

enum class PartType {
    STATIC,
    TEMPLATE
}

private class RoutePart {
    val type: PartType
    val value: String

    constructor(type: PartType, value: String) {
        this.type = type
        this.value = value
    }
}

val PayloadProperties = setOf<String>(
    "controller",
    "action",
    "meta",
    "volatile",
    "jwt",
    "requestId",
    "volatile",
    "body"
)

class Route {
    private val partType: PartType
    private val routeParts: ArrayList<RoutePart>
    private var staticURL: String = ""
    private var baseURL: String
    private var verb: String

    private constructor(verb: String, baseUrl: String, routeParts: ArrayList<RoutePart>) {
        this.verb = verb.uppercase()
        this.baseURL = baseUrl
        this.routeParts = routeParts
        val hasOnePartTemplated = routeParts.indexOfFirst { it.type == PartType.TEMPLATE } > -1
        this.partType = if (hasOnePartTemplated) PartType.TEMPLATE else PartType.STATIC
        if (! hasOnePartTemplated) {
            staticURL = routeParts.joinToString("") {
                it.value
            }
        }
    }

    /**
     * Given map containing the properties of the request, the buildRequest method
     * will create an instance of HttpRequest which contains the information needed to make
     * the request later on.
     *
     * The method has to:
     * - Generate the URL using the properties of the request if there are part of the URL that are templated
     * - Generate the query string
     * - Generate the list of headers
     */
    fun buildRequest(request: KuzzleMap): HttpRequest {
        val headers = request.optMap("headers", KuzzleMap())

        val queryArgs = KuzzleMap()
        for (key: String? in request.keys) {
            // Skip if a key or value is null
            if (key == null || request[key] == null) {
                continue
            }

            when (key) {
                "jwt" -> headers["authorization"] = "Bearer ${request["jwt"]}"
                "volatile" -> headers["x-kuzzle-volatile"] = request["volatile"]
                "requestId" -> headers["x-kuzzle-request-id"] = request["requestId"]
                "headers" -> headers.putAll(request.optMap("headers", KuzzleMap()))
                "body" -> {
                    if (verb == "GET") {
                        queryArgs.putAll(request.optMap("body", KuzzleMap()))
                    }
                }
                else -> {
                    if (! PayloadProperties.contains(key)) {
                        queryArgs[key] = request[key]
                    }
                }
            }
        }

        /**
         * Build the query string
         */
        val queryString: String = queryArgs.keys
            .filter {
                it != null && ! queryArgs.isArrayList(it) && ! queryArgs.isMap(it)
            }
            .joinToString("&") {
                val encodedKey = URLEncoder.encode(it, "utf-8")

                if (queryArgs.optBoolean(it!!, false) == true) {
                    encodedKey
                } else {
                    val value = queryArgs[it].toString()
                    "$encodedKey=${URLEncoder.encode(value, "utf-8")}"
                }
            }

        /**
         * If the partType is STATIC it means that there is no template in the url
         * So we can use the staticURL instead of building the URL
         */
        if (partType == PartType.STATIC) {
            return HttpRequest(
                verb,
                if (queryArgs.isEmpty()) staticURL else "$staticURL?$queryString",
                if (verb != "GET") request.optMap("body", KuzzleMap()) else null,
                headers
            )
        }

        /**
         * Each STATIC part will be appended to the string builder without further processing,
         * TEMPLATE parts however will be used to find their corresponding value in the request map
         * and the retrieve value will be put in the URL
         */
        val urlBuilder = StringBuilder()
        for (routePart: RoutePart in routeParts) {
            if (routePart.type == PartType.STATIC) {
                urlBuilder.append(routePart.value)
            } else {
                if (! request.containsKey(routePart.value) || request[routePart.value] == null) {
                    throw MissingURLParamException(routePart.value, baseURL)
                }
                val serializedValue = request[routePart.value]!!.toString()
                urlBuilder.append(URLEncoder.encode(serializedValue, "utf-8"))
            }
        }
        return HttpRequest(
            verb,
            if (queryArgs.isEmpty()) urlBuilder.toString() else "$urlBuilder?$queryString",
            if (verb != "GET") request.optMap("body", KuzzleMap()) else null,
            headers
        )
    }

    companion object {
        /**
         * Parse Kuzzle url with the format /:index/:collection
         * Split each section of the url and tag them either as STATIC or TEMPLATE
         * Static parts are static as the name suggest, they can be combined without further processing
         * Template parts however need to be replaced with the appropriate value
         * Each sections starting with : is considered to be a template part
         */
        fun parse(verb: String, url: String): Route {
            val routeParts = ArrayList<RoutePart>()

            // Sanitize path in case of double /
            val parts = url.split('/').filter { it.isNotEmpty() }

            routeParts.add(RoutePart(PartType.STATIC, "/"))

            for (part: String in parts) {
                if (part.startsWith(":")) {
                    routeParts.add(RoutePart(PartType.TEMPLATE, part.substring(1)))
                } else {
                    routeParts.add(RoutePart(PartType.STATIC, part))
                }
                // Add static / to separate each parts
                routeParts.add(RoutePart(PartType.STATIC, "/"))
            }

            // Remove trailing / if there was none in the url
            if (! url.endsWith("/")) {
                routeParts.removeLast()
            }

            return Route(verb, url, routeParts)
        }
    }
}
