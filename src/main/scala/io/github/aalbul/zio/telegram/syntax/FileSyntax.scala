package io.github.aalbul.zio.telegram.syntax

import io.github.aalbul.zio.telegram.domain.File
import io.github.aalbul.zio.telegram.engine.BotEngine
import io.github.aalbul.zio.telegram.engine.BotEngine.BotException
import zio.ZIO
import zio.stream.ZStream

trait FileSyntax {
  implicit class FileOps(file: File) {
    def stream: ZStream[BotEngine, Throwable, Byte] = ZStream.unwrap(
      file.filePath
        .map(path => ZIO.serviceWithZIO[BotEngine](_.streamFile(path)))
        .getOrElse(ZIO.fail(BotException(s"No file path found for file: ${file.fileId}")))
    )
  }
}
