package com.github.aalbul.zio.telegram.test

import com.github.aalbul.zio.telegram.command.ApiParameters
import io.circe.Json
import io.circe.parser.parse

import scala.io.{Codec, Source}

trait JsonTestingSupport {
  def jsonResource(path: String): Json = parse(Source.fromResource(path)(Codec.UTF8).getLines().mkString).toTry.get
  def jsonResourceString(path: String): String = jsonResource(path).printWith(ApiParameters.jsonPrinter)
}
