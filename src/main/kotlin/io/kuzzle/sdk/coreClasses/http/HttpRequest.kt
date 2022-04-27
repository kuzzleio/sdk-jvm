package io.kuzzle.sdk.coreClasses.http

import io.kuzzle.sdk.coreClasses.maps.KuzzleMap

data class HttpRequest(
    val verb: String,
    val url: String,
    val body: KuzzleMap?,
    val headers: KuzzleMap,
)
