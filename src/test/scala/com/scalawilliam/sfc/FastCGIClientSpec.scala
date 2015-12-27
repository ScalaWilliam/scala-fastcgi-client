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

  test("Raw POST request works") {
    val response = SampleRequest.sampleRequest.copy(
      servletPath = "/test2.php",
      method = "POST",
      headers = List("Content-Type" -> "text/plain"),
      data = Option("Some stuff")
    ).process()
    response.output.value shouldBe "Some stuff"
  }

  override protected def beforeAll(): Unit = {
    super.beforeAll()
    val address = "127.0.0.1:7776"
    fcgi = FastCGIHandlerConfig(
      connectionConfig = FastCGIConnectionConfig.SingleConnection(address = address),
      startExecutable = Option(
        if (scala.util.Properties.isWin) s"C:/php/php-cgi.exe -b ${address}"
        else s"php -b ${address}"
      )
    ).build
  }

  override protected def afterAll(): Unit = {
    fcgi.destroy()
    super.afterAll()
  }

}