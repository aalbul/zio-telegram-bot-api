package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.domain.{Update, UpdateType}
import io.github.aalbul.zio.telegram.engine.BotEngine
import io.github.aalbul.zio.telegram.syntax.all.*
import zio.Chunk
import zio.stream.ZStream

object StreamUpdates {
  def apply(chunkSize: Long = 100L, allowedTypes: Set[UpdateType] = Set.empty): ZStream[BotEngine, Throwable, Update] =
    ZStream.unfoldChunkZIO(0L) { offset =>
      for {
        updates <- GetUpdates.of.withLimit(chunkSize).withOffset(offset).withAllowedUpdates(allowedTypes)
        newOffset = updates.lastOption.map(_.updateId + 1).getOrElse(offset)
      } yield Some(Chunk.fromIterable(updates), newOffset)
    }
}
