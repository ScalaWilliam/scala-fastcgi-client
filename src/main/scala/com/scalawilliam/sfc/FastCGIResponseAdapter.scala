package com
package scalawilliam
package sfc

import java.io.{OutputStream, ByteArrayOutputStream}

import net.jr.fastcgi.impl.ResponseAdapter

/**
  * Created by William on 27/12/2015.
  */
private[sfc] class FastCGIResponseAdapter(var response: FastCGIResponse = FastCGIResponse.empty) extends ResponseAdapter {
  private val outputStream = new ByteArrayOutputStream()

  override def sendError(errorCode: Int): Unit = response = response.copy(error = Option(errorCode))

  override def setStatus(statusCode: Int): Unit = response = response.copy(status = Option(statusCode))

  override def addHeader(key: String, value: String): Unit = response = response.copy(headers = response.headers :+ (key -> value))

  override def sendRedirect(targetUrl: String): Unit = response = response.copy(redirect = Option(targetUrl))

  override def getOutputStream: OutputStream = outputStream

  def complete(): Unit = response = response.copy(output = Option(new String(outputStream.toByteArray, "UTF-8")))
}
