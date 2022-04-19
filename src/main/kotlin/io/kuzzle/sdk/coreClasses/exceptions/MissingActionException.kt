package io.kuzzle.sdk.coreClasses.exceptions

open class MissingActionException : KuzzleException {
    constructor() : super(KuzzleExceptionCode.MISSING_ACTION)
}
