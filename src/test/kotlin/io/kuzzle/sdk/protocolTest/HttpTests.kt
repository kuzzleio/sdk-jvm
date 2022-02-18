package io.kuzzle.sdk.protocolTest

import io.kuzzle.sdk.protocol.Http
import java.net.URI
import org.junit.Test

class HttpTest {
  @Test
  fun testConstructor() {
    val req = Http("http://webcode.me")
    req.send(mapOf("foo" to "bar"))
  }

}