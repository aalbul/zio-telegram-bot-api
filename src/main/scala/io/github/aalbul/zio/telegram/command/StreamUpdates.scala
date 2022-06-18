package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.domain.Update
import io.github.aalbul.zio.telegram.engine.BotEngine
import io.github.aalbul.zio.telegram.syntax.all.*
import zio.Chunk
import zio.stream.ZStream

object StreamUpdates {
  def apply(chunkSize: Long = 100L): ZStream[BotEngine, Throwable, Update] = ZStream.unfoldChunkZIO(0) { offset =>
    for {
      updates <- GetUpdates.of.withLimit(chunkSize).withOffset(offset)
      newOffset = updates.lastOption.map(_.updateId + 1).getOrElse(offset)
    } yield Some(Chunk.fromIterable(updates), newOffset)
  }
}
