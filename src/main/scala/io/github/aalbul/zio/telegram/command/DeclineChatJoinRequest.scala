package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.DeclineChatJoinRequest.DeclineChatJoinRequestPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object DeclineChatJoinRequest {
  object DeclineChatJoinRequestPayload {
    implicit val declineChatJoinRequestPayloadJsonCodec: JsonValueCodec[DeclineChatJoinRequestPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class DeclineChatJoinRequestPayload(chatId: String, userId: Long)

  /** Constructs minimal [[DeclineChatJoinRequest]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param userId
    *   Unique identifier of the target user
    * @return
    *   [[DeclineChatJoinRequest]] builder
    */
  def of(chatId: String, userId: Long): DeclineChatJoinRequest = DeclineChatJoinRequest(
    DeclineChatJoinRequestPayload(chatId = chatId, userId = userId)
  )
}

/** Use this method to decline a chat join request. The bot must be an administrator in the chat for this to work and
  * must have the can_invite_users administrator right. Returns True on success.
  */
case class DeclineChatJoinRequest(payload: DeclineChatJoinRequestPayload) extends Command[Boolean] {
  override val name: String = "declineChatJoinRequest"

  override def parameters: ApiParameters = JsonBody(payload)
}
