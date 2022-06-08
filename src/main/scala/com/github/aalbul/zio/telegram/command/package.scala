package com.github.aalbul.zio.telegram

import com.github.aalbul.zio.telegram.domain.JsonSerializationSupport
import com.github.aalbul.zio.telegram.engine.BotEngine
import io.circe.Decoder
import zio.{RIO, ZIO}

package object command extends JsonSerializationSupport {
  implicit class FileDescriptorOps(descriptor: FileDescriptor) {
    def asMultipart(name: String): MultipartBodyPart = descriptor match {
      case PathBasedFileDescriptor(path) => FilePart(name, path)
      case UrlFileDescriptor(url)        => StringPart(name, url)
      case IdFileDescriptor(url)         => StringPart(name, url)
    }
  }

  implicit class CommandOps[T: Decoder](command: Command[T]) {
    val execute: RIO[BotEngine, T] = ZIO.serviceWithZIO[BotEngine](_.execute(command))
  }
}
