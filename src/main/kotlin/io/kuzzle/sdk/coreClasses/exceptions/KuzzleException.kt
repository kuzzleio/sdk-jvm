package io.kuzzle.sdk.coreClasses.exceptions

/**
 * Root of all Kuzzle exceptions.
 */
open class KuzzleException : Exception {
    /**
     * Kuzzle API error code.
     */
    var status: Int
        protected set

    /**
     * Initializes a new instance of the KuzzleException.
     *
     * @param message
     * Message.
     * @param status
     * Status.
     */
    protected constructor(message: String?, status: Int) : super(message) {
        this.status = status
    }

    protected constructor(message: String?, status: KuzzleExceptionCode) : super(message) {
        this.status = status.code
    }

    protected constructor(status: KuzzleExceptionCode) : super(status.message) {
        this.status = status.code
    }

    companion object {
        private const val serialVersionUID = 4446507573441857492L
    }
}
