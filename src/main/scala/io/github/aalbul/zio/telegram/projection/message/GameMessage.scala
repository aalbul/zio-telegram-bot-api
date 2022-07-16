package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Game, Message}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object GameMessage {
  implicit val gameMessageProjector: MessageProjector[GameMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      game <- message.game
    } yield GameMessage(
      data = data,
      game = game
    )
}

case class GameMessage(data: Data, game: Game) extends MessageProjection
