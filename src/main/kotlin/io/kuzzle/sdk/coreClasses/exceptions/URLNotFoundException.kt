package io.kuzzle.sdk.coreClasses.exceptions

class URLNotFoundException : KuzzleException {
    constructor(controller: String, action: String) : super("No URL found for \"$controller:$action\".", 400)
}
