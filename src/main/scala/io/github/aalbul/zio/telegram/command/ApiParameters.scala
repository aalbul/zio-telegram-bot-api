package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.{writeToString, JsonValueCodec}

import java.nio.file.{Path, Paths}
import scala.concurrent.duration.Duration

sealed trait ApiParameters
case object NoParameters extends ApiParameters
case class JsonBody[T: JsonValueCodec](body: T) extends ApiParameters {
  def toJson: String = writeToString(body)
}

sealed trait MultipartBodyPart {
  val name: String
}

case class StringPart(name: String, value: String) extends MultipartBodyPart
case class FilePart(name: String, path: Path) extends MultipartBodyPart

object MultipartBody {
  def ofOpt(part: Option[MultipartBodyPart]*) = new MultipartBody(part.flatten)
  def of(parts: MultipartBodyPart*) = new MultipartBody(parts)
  def filePart(name: String, path: String): FilePart = FilePart(name, Paths.get(path))
  def stringPart(name: String, value: String): StringPart = StringPart(name, value)
  def stringPart(name: String, value: Boolean): StringPart = StringPart(name, value.toString)
  def stringPart(name: String, value: Long): StringPart = StringPart(name, value.toString)
  def stringPart(name: String, value: Duration): StringPart = StringPart(name, value.toSeconds.toString)
  def stringPart[T: JsonValueCodec](name: String, value: T): StringPart = StringPart(name, JsonBody(value).toJson)
}

case class MultipartBody(parts: Seq[MultipartBodyPart]) extends ApiParameters {
  def plus(extra: Seq[MultipartBodyPart]): MultipartBody = copy(parts = parts ++ extra)
}
