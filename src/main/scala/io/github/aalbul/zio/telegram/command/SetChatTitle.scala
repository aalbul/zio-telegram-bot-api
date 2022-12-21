package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.SetChatTitle.SetChatTitlePayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object SetChatTitle {
  object SetChatTitlePayload {
    implicit val setChatTitlePayloadJsonCodec: JsonValueCodec[SetChatTitlePayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class SetChatTitlePayload(chatId: String, title: String)

  /** Constructs minimal [[SetChatTitle]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param title
    *   New chat title, 1-128 characters
    * @return
    *   [[SetChatTitle]] builder
    */
  def of(chatId: String, title: String): SetChatTitle = SetChatTitle(
    SetChatTitlePayload(chatId = chatId, title = title)
  )
}

/** Use this method to change the title of a chat. Titles can't be changed for private chats. The bot must be an
  * administrator in the chat for this to work and must have the appropriate administrator rights. Returns True on
  * success.
  */
case class SetChatTitle(payload: SetChatTitlePayload) extends Command[Boolean] {
  override val name: String = "setChatTitle"

  override def parameters: ApiParameters = JsonBody(payload)
}
