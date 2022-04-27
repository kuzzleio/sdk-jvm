package io.kuzzle.sdk.coreClasses.exceptions

class URLNotFoundException(controller: String, action: String) : KuzzleException("No URL found for \"$controller:$action\".", 400)
