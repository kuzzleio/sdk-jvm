package io.kuzzle.sdk.events

import io.kuzzle.sdk.coreClasses.responses.Response

data class UnhandledResponseEvent(var message: Response?)
