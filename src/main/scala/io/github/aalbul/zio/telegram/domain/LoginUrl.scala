package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object LoginUrl {

  /** Constructs minimal [[LoginUrl]]
    * @param url
    *   An HTTPS URL to be opened with user authorization data added to the query string when the button is pressed. If
    *   the user refuses to provide authorization data, the original URL without information about the user will be
    *   opened. The data added is the same as described in
    *   [[https://core.telegram.org/widgets/login#receiving-authorization-data Receiving authorization data]]. NOTE: You
    *   must always check the hash of the received data to verify the authentication and the integrity of the data as
    *   described in [[https://core.telegram.org/widgets/login#checking-authorization Checking authorization]].
    */
  def of(url: String): LoginUrl = LoginUrl(
    url = url,
    forwardText = None,
    botUsername = None,
    requestWriteAccess = None
  )

  implicit val loginUrlJsonCodec: JsonValueCodec[LoginUrl] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

/** This object represents a parameter of the inline keyboard button used to automatically authorize a user. Serves as a
  * great replacement for the [[https://core.telegram.org/widgets/login Telegram Login Widget]] when the user is coming
  * from Telegram. All the user needs to do is tap/click a button and confirm that they want to log in:
  *
  * Telegram apps support these buttons as of
  * [[https://telegram.org/blog/privacy-discussions-web-bots#meet-seamless-web-bots version 5.7]].
  */
case class LoginUrl(
  url: String,
  forwardText: Option[String],
  botUsername: Option[String],
  requestWriteAccess: Option[Boolean]
) {

  /** New text of the button in forwarded messages. */
  def withForwardText(text: String): LoginUrl = copy(forwardText = Some(text))

  /** Username of a bot, which will be used for user authorization. See
    * [[https://core.telegram.org/widgets/login#setting-up-a-bot Setting up a bot]] for more details. If not specified,
    * the current bot's username will be assumed. The url's domain must be the same as the domain linked with the bot.
    * See [[https://core.telegram.org/widgets/login#linking-your-domain-to-the-bot Linking your domain]] to the bot for
    * more details.
    */
  def withBotUsername(userName: String): LoginUrl = copy(botUsername = Some(userName))

  /** Pass True to request the permission for your bot to send messages to the user. */
  def withRequestWriteAccess(request: Boolean): LoginUrl = copy(requestWriteAccess = Some(request))
}
