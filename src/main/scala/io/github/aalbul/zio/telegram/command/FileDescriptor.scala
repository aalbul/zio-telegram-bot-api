package io.github.aalbul.zio.telegram.command

import io.circe.Encoder

import java.nio.file.{Path, Paths}

object FileDescriptor {
  def pathDescriptor(path: String): FileDescriptor = PathBasedFileDescriptor(Paths.get(path))
  def urlDescriptor(url: String): FileDescriptor = UrlFileDescriptor(url)
  def idDescriptor(id: String): FileDescriptor = IdFileDescriptor(id)

  implicit val fileDescriptorEncoder: Encoder[FileDescriptor] = Encoder.encodeString.contramap {
    case PathBasedFileDescriptor(path) => s"attach://${path.toAbsolutePath}"
    case UrlFileDescriptor(url)        => url
    case IdFileDescriptor(id)          => id
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
