package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object CallbackGame {
  implicit val callbackGameJsonCodec: JsonValueCodec[CallbackGame] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[CallbackGame]] object
    * @return
    *   [[CallbackGame]] builder
    */
  def of: CallbackGame = CallbackGame()
}

/** A placeholder, currently holds no information. Use [[https://t.me/botfather BotFather]] to set up your game.
  */
case class CallbackGame()
