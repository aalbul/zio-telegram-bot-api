package com.github.aalbul.zio.telegram.test

import io.circe.Json
import io.circe.parser.parse

import scala.io.{Codec, Source}

trait JsonTestingSupport {
  def jsonResource(path: String): Json = parse(Source.fromResource(path)(Codec.UTF8).getLines().mkString).toTry.get
}
