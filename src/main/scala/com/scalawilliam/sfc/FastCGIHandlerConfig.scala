package com
package scalawilliam
package sfc

import net.jr.fastcgi.impl.FastCGIHandlerFactory

/**
  * Created by William on 27/12/2015.
  */
case class FastCGIHandlerConfig(connectionConfig: FastCGIConnectionConfig, filteredHeaders: Option[List[String]] = None, startExecutable: Option[String] = None) {
  def toMap = Map(
    connectionConfig.key -> connectionConfig.value
  ) ++ {
    filteredHeaders.map(fh => FastCGIHandlerFactory.PARAM_FILTERED_HEADERS -> fh.mkString(";"))
  } ++ {
    startExecutable.map(ex => FastCGIHandlerFactory.PARAM_START_EXECUTABLE -> ex)
  }
  def toJavaMap: java.util.Map[String, String] = {
    import collection.JavaConverters._
    toMap.asJava
  }
  def build = FastCGIHandlerFactory.create(toJavaMap)
}
