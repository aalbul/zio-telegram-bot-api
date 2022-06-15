package com.github.aalbul.zio.telegram.domain

import io.circe.Encoder

object DiceTypes extends Enumeration {
  implicit val diceTypeEncoder: Encoder[DiceType] = Encoder.encodeString.contramap {
    case DirectHit   => "🎯"
    case Basketball  => "🏀"
    case Football    => "⚽"
    case Bowling     => "🎳"
    case SlotMachine => "🎰"
    case _           => "🎲"
  }

  type DiceType = Value

  val Die, DirectHit, Basketball, Football, Bowling, SlotMachine = Value
}
