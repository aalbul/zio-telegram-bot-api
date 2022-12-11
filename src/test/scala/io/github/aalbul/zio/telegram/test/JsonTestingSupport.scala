package io.github.aalbul.zio.telegram.test

import io.circe.parser.parse
import io.circe.{Decoder, Json}
import io.github.aalbul.zio.telegram.command.ApiParameters

import scala.io.{Codec, Source}

trait JsonTestingSupport {
  def jsonResourceAs[T: Decoder](path: String): T = jsonResource(path).as[T].toTry.get
  def jsonResource(path: String): Json = parse(Source.fromResource(path)(Codec.UTF8).getLines().mkString).toTry.get
  def jsonResourceString(path: String): String = jsonResource(path).printWith(ApiParameters.jsonPrinter)
}
