package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
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
}

/** Describes a [[https://core.telegram.org/bots/webapps Web App]].
  */
@ConfiguredJsonCodec
case class WebAppInfo(url: String)
