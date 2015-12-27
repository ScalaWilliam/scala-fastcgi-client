scala-fastcgi-client
=
[![Build Status](https://travis-ci.org/ScalaWilliam/scala-fastcgi-client.svg)](https://travis-ci.org/ScalaWilliam/scala-fastcgi-client)

A synchronous FastCGI client for Scala.

Problem space
==
Scala simply doesn't cut it when you want to generate HTML or get newbies to get anything done.
Large projects in PHP are very difficult.

We want minimalistic straightforward access to PHP's FastCGI interface so we can call PHP from Scala.
Case class in, case class out = minimal solution to the problem.

Synchronous, based on jFastCGI
==
It is built on top of jFastCGI: https://github.com/jFastCGI/jfastcgi

Don't be scared. Even Slick JDBC is synchronous underneath.

If you want asynchronous, consider: https://github.com/leanovate/toehold

Usage
==

Add the following to your build.sbt:

```scala
resolvers += Resolver.bintrayRepo("scalawilliam", "maven")
libraryDependencies += "com.scalawilliam" %% "scala-fastcgi-client" % "0.3"
```


Basic usage (src/test/com/scalawilliam/example/SampleApp.scala)

```scala
import com.scalawilliam.sfc._
import com.scalawilliam.sfc.Implicits._

object SampleApp extends App {
  implicit val fcgi = FastCGIHandlerConfig(
    connectionConfig = FastCGIConnectionConfig.SingleConnection(
      address = "127.0.0.1:7776"
    )
  ).build
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
  // Output = FastCGIResponse(None,None,List((X-Powered-By,PHP/7.0.0), (X-Test,Test), (Content-type,text/html; charset=UTF-8)),None,Some(Received))
  try println(sampleRequest.process())
  finally fcgi.destroy()
}
```

License
==
Apache License 2.0 - Same as jFastCGI