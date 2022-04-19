package io.kuzzle.sdk.coreClasses.exceptions

open class MissingControllerException : KuzzleException {
    constructor() : super(KuzzleExceptionCode.MISSING_CONTROLLER)
}
