package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object KeyboardButtonPollType {
  implicit val keyboardButtonPollTypeJsonCodec: JsonValueCodec[KeyboardButtonPollType] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[KeyboardButtonPollType]]
    * @param `type`
    *   If quiz is passed, the user will be allowed to create only polls in the quiz mode. If regular is passed, only
    *   regular polls will be allowed. Otherwise, the user will be allowed to create a poll of any type.
    * @return
    *   [[KeyboardButtonPollType]] builder
    */
  def of(`type`: PollType): KeyboardButtonPollType = KeyboardButtonPollType(`type`)
}

/** This object represents type of a poll, which is allowed to be created and sent when the corresponding button is
  * pressed.
  */
case class KeyboardButtonPollType(`type`: PollType)
