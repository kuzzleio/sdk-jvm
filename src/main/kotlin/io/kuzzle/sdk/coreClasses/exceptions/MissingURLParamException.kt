package io.kuzzle.sdk.coreClasses.exceptions

/**
 * Thrown when attempting to interact with the network while not connected.
 */
class MissingURLParamException(templateParam: String, baseURL: String) : KuzzleException("Missing URL Param $templateParam in $baseURL", 0)
