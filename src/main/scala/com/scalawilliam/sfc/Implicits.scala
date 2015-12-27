package com
package scalawilliam
package sfc

import net.jr.fastcgi.impl.FastCGIHandler

/**
  * Created by William on 27/12/2015.
  */
object Implicits {
  implicit class scalaInputProcessor(scalaInput: FastCGIRequest) {
    def process()(implicit fastCGIHandler: FastCGIHandler): FastCGIResponse = {
      val scalaOutput = new FastCGIResponseAdapter()
      fastCGIHandler.service(scalaInput, scalaOutput)
      scalaOutput.complete()
      scalaOutput.response
    }
  }
}
