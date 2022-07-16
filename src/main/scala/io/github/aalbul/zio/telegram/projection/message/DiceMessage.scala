package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Dice, Message}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object DiceMessage {
  implicit val diceMessageProjector: MessageProjector[DiceMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      dice <- message.dice
    } yield DiceMessage(
      data = data,
      dice = dice
    )
}

case class DiceMessage(data: Data, dice: Dice) extends MessageProjection
