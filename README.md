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
  // Input = FastCGIRequest(None,List(),None,None,,/test.php,/uri/,ScalaTest,HTTP/1.1,127.0.0.1,1234,GET,None,<function1>)
  println(SampleRequest.sampleRequest)
  // Output = FastCGIResponse(None,None,List((X-Powered-By,PHP/7.0.0), (X-Test,Test), (Content-type,text/html; charset=UTF-8)),None,Some(Received))
  try println(SampleRequest.sampleRequest.process())
  finally fcgi.destroy()
}
```

License
==
Apache License 2.0 - Same as jFastCGI