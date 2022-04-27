package io.kuzzle.sdk.coreClasses.exceptions

class InvalidJSON(json: String) : KuzzleException("Invalid JSON \"$json\".", 0)
