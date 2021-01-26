package io.kuzzle.sdk.handlers

import io.kuzzle.sdk.coreClasses.responses.Response

/**
 * Interface to be used with realtime:subscribe
 * to make it more usable in JAVA
 */
interface NotificationHandler {
    fun run(notification: Response)
}
