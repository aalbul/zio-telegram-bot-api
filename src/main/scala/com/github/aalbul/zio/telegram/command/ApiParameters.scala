package com.github.aalbul.zio.telegram.command

import com.github.aalbul.zio.telegram.command.ApiParameters.jsonPrinter
import io.circe.syntax.EncoderOps
import io.circe.{Encoder, Printer}

import java.nio.file.Path

object ApiParameters {
  val jsonPrinter: Printer = Printer.noSpaces.copy(dropNullValues = true)
}

sealed trait ApiParameters
case object NoParameters extends ApiParameters
case class JsonBody[T: Encoder](body: T) extends ApiParameters {
  def toJson: String = body.asJson.printWith(jsonPrinter)
}

sealed trait MultipartBodyPart {
  val name: String
}

case class StringPart(name: String, value: String) extends MultipartBodyPart
case class FilePart(name: String, path: Path) extends MultipartBodyPart

object MultipartBody {
  def of(part: Option[MultipartBodyPart]*) = new MultipartBody(part.flatten)
  def stringPart(name: String, value: String): StringPart = StringPart(name, value)
  def stringPart(name: String, value: Boolean): StringPart = StringPart(name, value.toString)
  def stringPart(name: String, value: Long): StringPart = StringPart(name, value.toString)
}

case class MultipartBody(parts: Seq[MultipartBodyPart]) extends ApiParameters
