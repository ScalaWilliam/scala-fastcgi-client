package com
package scalawilliam
package sfc

import net.jr.fastcgi.impl.FastCGIHandlerFactory

/**
  * Created by William on 27/12/2015.
  */

sealed abstract class FastCGIConnectionConfig(val key: String) {
  def value: String
}

object FastCGIConnectionConfig {
  case class SingleConnection(address: String) extends FastCGIConnectionConfig(FastCGIHandlerFactory.PARAM_SERVER_ADDRESS) {
    def value = address
  }
  case class CustomConnectionFactory(className: String) extends FastCGIConnectionConfig(FastCGIHandlerFactory.PARAM_CONNECTION_FACTORY) {
    def value = className
  }
  case class MultipleAddresses(first: String, rest: String*) extends FastCGIConnectionConfig(FastCGIHandlerFactory.PARAM_CLUSTER_ADRESSES) {
    def value = (first :+ rest).mkString(";")
  }
}
