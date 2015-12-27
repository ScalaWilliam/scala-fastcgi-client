package com
package scalawilliam
package sfc

/**
  * Created by William on 27/12/2015.
  */
object SampleRequest {
  val sampleRequest = FastCGIRequest(
    remoteUser = None,
    headers = List.empty,
    authType = Option.empty,
    queryString = None,
    contextPath = "",
    servletPath = "/test.php",
    requestURI = "/uri/",
    serverName = "ScalaTest",
    protocol = "HTTP/1.1",
    remoteAddr = "127.0.0.1",
    serverPort = 1234,
    method = "GET",
    data = None,
    realPath = something => {
      scala.util.Properties.userDir + "/src/test/resources/root" + something
    }
  )
}
