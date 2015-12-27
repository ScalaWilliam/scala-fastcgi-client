package com
package scalawilliam
package example

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
