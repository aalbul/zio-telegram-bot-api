package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import enumeratum.{EnumEntry, *}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

sealed trait DiceType extends EnumEntry

object DiceType extends Enum[DiceType] {
  case object Die extends DiceType
  case object DirectHit extends DiceType
  case object Basketball extends DiceType
  case object Football extends DiceType
  case object Bowling extends DiceType
  case object SlotMachine extends DiceType

  override def values: IndexedSeq[DiceType] = findValues

  implicit lazy val diceTypeJsonCodec: JsonValueCodec[DiceType] = makeCodecFromRelations(
    "🎯" -> DirectHit,
    "🏀" -> Basketball,
    "⚽" -> Football,
    "🎳" -> Bowling,
    "🎰" -> SlotMachine,
    "🎲" -> Die
  )
}
