package com
package scalawilliam
package sfc

import net.jr.fastcgi.impl.FastCGIHandler
import org.scalatest.{BeforeAndAfterAll, FunSuite, Matchers, OptionValues}
import Implicits._

class FastCGIClientSpec
  extends FunSuite
  with Matchers
  with BeforeAndAfterAll
  with OptionValues {

  implicit var fcgi: FastCGIHandler = _

  test("Basic GET request works") {
    val response = SampleRequest.sampleRequest.process()
    response.error shouldBe empty
    response.output.value shouldBe "Received"
    response.redirect shouldBe empty
    response.headers should contain("X-Test" -> "Test")
    response.status shouldBe empty
  }

  test("POST request works") {
    val response = SampleRequest.sampleRequest.copy(
      method = "POST",
      headers = List("Content-Type" -> "application/x-www-form-urlencoded"),
      data = Option("a=1&b=2")
    ).process()
    response.output.value shouldBe "a=2&b=3"
  }

  override protected def beforeAll(): Unit = {
    super.beforeAll()
    fcgi = FastCGIHandlerConfig(FastCGIConnectionConfig.SingleConnection(address = "127.0.0.1:7776")).build
  }

  override protected def afterAll(): Unit = {
    fcgi.destroy()
    super.afterAll()
  }

}