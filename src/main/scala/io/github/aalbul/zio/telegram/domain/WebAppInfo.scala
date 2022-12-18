package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object WebAppInfo {

  /** Constructs minimal [[WebAppInfo]]
    * @param url
    *   An HTTPS URL of a Web App to be opened with additional data as specified in
    *   [[https://core.telegram.org/bots/webapps#initializing-web-apps Initializing Web Apps]]
    * @return
    *   [[WebAppInfo]] builder
    */
  def of(url: String): WebAppInfo = WebAppInfo(url = url)

  implicit val webAppInfoJsonCodec: JsonValueCodec[WebAppInfo] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

/** Describes a [[https://core.telegram.org/bots/webapps Web App]].
  */
case class WebAppInfo(url: String)
