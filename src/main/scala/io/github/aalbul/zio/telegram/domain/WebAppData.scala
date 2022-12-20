package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object WebAppData {
  implicit val webAppDataJsonCodec: JsonValueCodec[WebAppData] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[WebAppData]]
    * @param data
    *   The data. Be aware that a bad client can send arbitrary data in this field.
    * @param buttonText
    *   Text of the web_app keyboard button from which the Web App was opened. Be aware that a bad client can send
    *   arbitrary data in this field.
    * @return
    *   [[WebAppData]] builder
    */
  def of(data: String, buttonText: String): WebAppData = WebAppData(
    data = data,
    buttonText = buttonText
  )
}

/** Describes data sent from a [[https://core.telegram.org/bots/webapps Web App]] to the bot.
  */
case class WebAppData(data: String, buttonText: String)
