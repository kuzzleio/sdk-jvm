package io.kuzzle.sdk.coreClasses.exceptions

import io.kuzzle.sdk.coreClasses.responses.Response

/**
 * Passed to async tasks when an API request returns an error.
 */
class ApiErrorException : KuzzleException {
    companion object {
        private const val serialVersionUID = 666379398727075901L
    }

    /**
     * Kuzzle API stack trace
     */
    var stack: String? = null
        private set

    /**
     * Kuzzle API error unique identifier
     */
    var id: String? = null
        private set

    override fun toString(): String {
        return "ApiErrorException \"$id\": $message\n$stack"
    }

    /**
     * Initializes a new instance of the ApiErrorException
     *
     * @param response Kuzzle API Response.
     */
    constructor(response: Response) :
        super(response.error?.message, response.status) {
            if (response.error != null) {
                this.stack = response.error!!.stack
                this.id = response.error!!.id
            }
        }
}
