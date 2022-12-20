package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object Dice {
  implicit val diceJsonCodec: JsonValueCodec[Dice] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[Dice]] object
    * @param emoji
    *   Emoji on which the dice throw animation is based
    * @param value
    *   Value of the dice, 1-6 for “🎲”, “🎯” and “🎳” base emoji, 1-5 for “🏀” and “⚽” base emoji, 1-64 for “🎰” base
    *   emoji
    * @return
    *   [[Dice]] builder
    */
  def of(emoji: String, value: Int): Dice = Dice(emoji = emoji, value = value)
}

/** This object represents an animated emoji that displays a random value.
  */
case class Dice(emoji: String, value: Int)
