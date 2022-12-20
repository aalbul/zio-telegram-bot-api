package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object InlineKeyboardMarkup {
  implicit val inlineKeyboardMarkupJsonCodec: JsonValueCodec[InlineKeyboardMarkup] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[InlineKeyboardMarkup]]
    * @param inlineKeyboard
    *   Array of button rows, each represented by an Array of
    *   [[https://core.telegram.org/bots/api#inlinekeyboardbutton InlineKeyboardButton]] objects
    * @return
    *   [[InlineKeyboardMarkup]] builder
    */
  def of(inlineKeyboard: Seq[Seq[InlineKeyboardButton]]): InlineKeyboardMarkup = InlineKeyboardMarkup(inlineKeyboard)
}

/** This object represents an [[https://core.telegram.org/bots/features#inline-keyboards inline keyboard]] that appears
  * right next to the message it belongs to.
  */
case class InlineKeyboardMarkup(inlineKeyboard: Seq[Seq[InlineKeyboardButton]]) extends Markup
