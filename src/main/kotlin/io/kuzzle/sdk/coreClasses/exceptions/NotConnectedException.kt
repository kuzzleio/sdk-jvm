package io.kuzzle.sdk.coreClasses.exceptions

/**
 * Thrown when attempting to interact with the network while not connected.
 */
open class NotConnectedException : KuzzleException {
  /**
   *
   */
  private val serialVersionUID = 1961824705891656436L

  constructor() : super(KuzzleExceptionCode.NOT_CONNECTED)
}