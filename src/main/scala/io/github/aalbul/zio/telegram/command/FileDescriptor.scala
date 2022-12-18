package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.{JsonReader, JsonValueCodec, JsonWriter}

import java.nio.file.{Path, Paths}

object FileDescriptor {
  def pathDescriptor(path: String): FileDescriptor = PathBasedFileDescriptor(Paths.get(path))
  def urlDescriptor(url: String): FileDescriptor = UrlFileDescriptor(url)
  def idDescriptor(id: String): FileDescriptor = IdFileDescriptor(id)

  implicit val fileDescriptorJsonCodec: JsonValueCodec[FileDescriptor] = new JsonValueCodec[FileDescriptor] {
    override def decodeValue(in: JsonReader, default: FileDescriptor): FileDescriptor = {
      val string = in.readString(null)
      if (string.startsWith("attach://")) pathDescriptor(string.drop("attach://".length))
      else if (string.contains("://")) urlDescriptor(string)
      else idDescriptor(string)
    }
    override def encodeValue(x: FileDescriptor, out: JsonWriter): Unit = out.writeVal(x match {
      case PathBasedFileDescriptor(path) => s"attach://${path.toAbsolutePath}"
      case UrlFileDescriptor(url)        => url
      case IdFileDescriptor(id)          => id
    })
    override def nullValue: FileDescriptor = null
  }
}

sealed trait FileDescriptor {
  def asMultipart(name: String): MultipartBodyPart
}
case class PathBasedFileDescriptor(path: Path) extends FileDescriptor {
  override def asMultipart(name: String): MultipartBodyPart = FilePart(name, path)
}
case class UrlFileDescriptor(url: String) extends FileDescriptor {
  override def asMultipart(name: String): MultipartBodyPart = StringPart(name, url)
}
case class IdFileDescriptor(id: String) extends FileDescriptor {
  override def asMultipart(name: String): MultipartBodyPart = StringPart(name, id)
}
