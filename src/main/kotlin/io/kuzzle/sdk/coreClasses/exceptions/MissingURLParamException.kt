package io.kuzzle.sdk.coreClasses.exceptions

/**
 * Thrown when attempting to interact with the network while not connected.
 */
class MissingURLParamException : KuzzleException {
    constructor(templateParam: String, baseURL: String) : super("Missing URL Param $templateParam in $baseURL", 0)
}
