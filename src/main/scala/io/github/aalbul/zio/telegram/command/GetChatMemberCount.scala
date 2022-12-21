package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.GetChatMemberCount.GetChatMemberCountPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object GetChatMemberCount {
  object GetChatMemberCountPayload {
    implicit val getChatMemberCountPayloadJsonCodec: JsonValueCodec[GetChatMemberCountPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class GetChatMemberCountPayload(chatId: String)

  /** Constructs minimal [[GetChatMemberCount]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup or channel (in the format
    *   \@channelusername)
    * @return
    *   [[GetChatMemberCount]] builder
    */
  def of(chatId: String): GetChatMemberCount = GetChatMemberCount(GetChatMemberCountPayload(chatId = chatId))
}

/** Use this method to get the number of members in a chat. Returns Int on success.
  */
case class GetChatMemberCount(payload: GetChatMemberCountPayload) extends Command[Long] {
  override val name: String = "getChatMemberCount"

  override def parameters: ApiParameters = JsonBody(payload)
}
