package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.SetChatMenuButton.SetChatMenuButtonPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.MenuButton

object SetChatMenuButton {
  object SetChatMenuButtonPayload {
    implicit val setChatMenuButtonPayloadJsonCodec: JsonValueCodec[SetChatMenuButtonPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class SetChatMenuButtonPayload(chatId: Option[String], menuButton: Option[MenuButton])

  /** Constructs minimal [[SetChatMenuButton]] command
    * @return
    *   [[SetChatMenuButton]] builder
    */
  def of: SetChatMenuButton = SetChatMenuButton(SetChatMenuButtonPayload(chatId = None, menuButton = None))
}

/** Use this method to change the bot's menu button in a private chat, or the default menu button. Returns True on
  * success.
  */
case class SetChatMenuButton(payload: SetChatMenuButtonPayload) extends Command[Boolean] {
  override val name: String = "setChatMenuButton"
  override def parameters: ApiParameters = JsonBody(payload)

  /** Unique identifier for the target private chat. If not specified, default bot's menu button will be changed
    */
  def withChatId(chatId: String): SetChatMenuButton = copy(payload.copy(chatId = Some(chatId)))

  /** A JSON-serialized object for the bot's new menu button. Defaults to
    * [[https://core.telegram.org/bots/api#menubuttondefault MenuButtonDefault]]
    */
  def withMenuButton(menuButton: MenuButton): SetChatMenuButton = copy(payload.copy(menuButton = Some(menuButton)))
}
