package com
package scalawilliam
package sfc

/**
  * Created by William on 27/12/2015.
  */
case class FastCGIResponse
(error: Option[Int],
 status: Option[Int],
 headers: List[(String, String)],
 redirect: Option[String],
 output: Option[String])

object FastCGIResponse {
  def empty = FastCGIResponse(
    error = None,
    status = None,
    headers = List.empty,
    redirect = None,
    output = None
  )
}
