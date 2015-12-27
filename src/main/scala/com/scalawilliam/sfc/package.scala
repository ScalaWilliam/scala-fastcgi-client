package com
package scalawilliam

import java.util

/**
  * Created by William on 27/12/2015.
  */
package object sfc {

  implicit class iterAsEnum[T](it: util.Iterator[T]) extends util.Enumeration[T] {
    override def hasMoreElements: Boolean = it.hasNext

    override def nextElement(): T = it.next()
  }

}
