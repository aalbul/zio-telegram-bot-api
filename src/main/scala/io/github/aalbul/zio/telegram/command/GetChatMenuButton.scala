package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.GetChatMenuButton.GetChatMenuButtonPayload
import io.github.aalbul.zio.telegram.domain.MenuButton

object GetChatMenuButton {
  object GetChatMenuButtonPayload {
    implicit val getChatMenuButtonPayloadJsonCodec: JsonValueCodec[GetChatMenuButtonPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class GetChatMenuButtonPayload(chatId: Option[String])

  /** Constructs minimal [[GetChatMenuButton]] command
    * @return
    *   [[GetChatMenuButton]] builder
    */
  def of(): GetChatMenuButton = GetChatMenuButton(GetChatMenuButtonPayload(chatId = None))
}

case class GetChatMenuButton(payload: GetChatMenuButtonPayload) extends Command[MenuButton] {
  override val name: String = "getChatMenuButton"
  override def parameters: ApiParameters = JsonBody(payload)

  /** Unique identifier for the target private chat. If not specified, default bot's menu button will be returned
    */
  def withChatId(chatId: String): GetChatMenuButton = copy(payload.copy(chatId = Some(chatId)))
}
