package com
package scalawilliam
package sfc

import java.io.{InputStream, ByteArrayInputStream}

import net.jr.fastcgi.impl.RequestAdapter
import collection.JavaConverters._
import scala.beans.BeanProperty

/**
  * Created by William on 27/12/2015.
  */
case class FastCGIRequest(remoteUser: Option[String],
                          headers: List[(String, String)],
                          authType: Option[String],
                          queryString: Option[String],
                          @BeanProperty contextPath: String,
                          @BeanProperty servletPath: String,
                          @BeanProperty requestURI: String,
                          @BeanProperty serverName: String,
                          @BeanProperty protocol: String,
                          @BeanProperty remoteAddr: String,
                          @BeanProperty serverPort: Int,
                          @BeanProperty method: String,
                          data: Option[String],
                          realPath: String => String
                         ) extends RequestAdapter {

  val bytes = data.getOrElse("").getBytes("UTF-8")

  private val inputStream = new ByteArrayInputStream(bytes)

  override def getHeaderNames: java.util.Enumeration[String] = headers.collect { case (key, _) => key }.toIterator.asJava

  override def getAuthType: String = authType.orNull

  override def getInputStream: InputStream = inputStream

  override def getHeader(key: String): String = headers.collectFirst { case (`key`, value) => value }.orNull

  override def getRealPath(relPath: String): String = realPath(relPath)

  override def getRemoteUser: String = remoteUser.orNull

  override def getQueryString: String = queryString.orNull

  override def getContentLength: Int = bytes.length

}
