package io.kuzzle.sdk.coreClasses.exceptions

enum class KuzzleExceptionCode {
    MISSING_REQUESTID(0, "Missing field requestId"), MISSING_QUERY(400, "You must provide a query"), NOT_CONNECTED(500, "Not connected."), CONNECTION_LOST(500, "Connection lost"), WRONG_VOLATILE_TYPE(
        400,
        "Volatile data must be a ConcurrentHashMap<String, Object>"
    );

    val code: Int
    val message: String?

    constructor(code: Int) {
        this.code = code
        message = null
    }

    constructor(code: Int, message: String?) {
        this.code = code
        this.message = message
    }
}
