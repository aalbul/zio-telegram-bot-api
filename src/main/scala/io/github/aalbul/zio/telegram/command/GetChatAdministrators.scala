package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.GetChatAdministrators.GetChatAdministratorsPayload
import io.github.aalbul.zio.telegram.domain.ChatMember

object GetChatAdministrators {
  object GetChatAdministratorsPayload {
    implicit val getChatAdministratorsPayloadJsonCodec: JsonValueCodec[GetChatAdministratorsPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class GetChatAdministratorsPayload(chatId: String)

  /** Constructs minimal [[GetChatAdministrators]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup or channel (in the format
    *   \@channelusername)
    * @return
    *   [[GetChatAdministrators]] builder
    */
  def of(chatId: String): GetChatAdministrators = GetChatAdministrators(GetChatAdministratorsPayload(chatId = chatId))
}

/** Use this method to get a list of administrators in a chat, which aren't bots. Returns an Array of
  * [[https://core.telegram.org/bots/api#chatmember ChatMember]] objects.
  */
case class GetChatAdministrators(payload: GetChatAdministratorsPayload) extends Command[Seq[ChatMember]] {
  override val name: String = "getChatAdministrators"

  override def parameters: ApiParameters = JsonBody(payload)
}
