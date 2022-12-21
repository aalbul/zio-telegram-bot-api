package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.GetChatMember.GetChatMemberPayload
import io.github.aalbul.zio.telegram.domain.ChatMember

object GetChatMember {
  object GetChatMemberPayload {
    implicit val getChatMemberPayloadJsonCodec: JsonValueCodec[GetChatMemberPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class GetChatMemberPayload(chatId: String, userId: Long)

  /** Constructs minimal [[GetChatMember]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup or channel (in the format
    *   \@channelusername)
    * @param userId
    *   Unique identifier of the target user
    * @return
    *   [[GetChatMember]] builder
    */
  def of(chatId: String, userId: Long): GetChatMember = GetChatMember(
    GetChatMemberPayload(chatId = chatId, userId = userId)
  )
}

/** Use this method to get information about a member of a chat. Returns a
  * [[https://core.telegram.org/bots/api#chatmember ChatMember]] object on success.
  */
case class GetChatMember(payload: GetChatMemberPayload) extends Command[ChatMember] {
  override val name: String = "getChatMember"

  override def parameters: ApiParameters = JsonBody(payload)
}
