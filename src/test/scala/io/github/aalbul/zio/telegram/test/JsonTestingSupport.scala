package io.github.aalbul.zio.telegram.test

import com.github.plokhotnyuk.jsoniter_scala.core.{JsonValueCodec, readFromString}
import com.stephenn.scalatest.circe.JsonMatchers.matchJson
import io.circe.parser.parse
import io.circe.{Decoder, Json}

import scala.io.{Codec, Source}

trait JsonTestingSupport {
  def jsonResourceAs[T: JsonValueCodec](path: String): T = readFromString(jsonResourceString(path))
  def jsonResource(path: String): Json = parse(Source.fromResource(path)(Codec.UTF8).getLines().mkString).toTry.get
  def jsonResourceString(path: String): String = Source.fromResource(path)(Codec.UTF8).getLines().mkString
  def matchJsonResource(path: String) = matchJson(jsonResourceString(path))
}
