package com.github.aalbul.zio.telegram.command

import com.github.aalbul.zio.telegram.domain.Update
import com.github.aalbul.zio.telegram.engine.BotEngine
import zio.Chunk
import zio.stream.ZStream

object StreamUpdates {
  def apply(chunkSize: Long = 100L): ZStream[BotEngine, Throwable, Update] = ZStream.unfoldChunkZIO(0) { offset =>
    for {
      updates <- GetUpdates.of().withLimit(chunkSize).withOffset(offset).execute
      newOffset = updates.lastOption.map(_.updateId + 1).getOrElse(offset)
    } yield Some(Chunk.fromIterable(updates), newOffset)
  }
}
