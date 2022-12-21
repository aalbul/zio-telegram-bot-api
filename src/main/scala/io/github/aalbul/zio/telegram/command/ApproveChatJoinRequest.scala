package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.ApproveChatJoinRequest.ApproveChatJoinRequestPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.codecs.boolean

object ApproveChatJoinRequest {
  object ApproveChatJoinRequestPayload {
    implicit val approveChatJoinRequestPayloadJsonCodec: JsonValueCodec[ApproveChatJoinRequestPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class ApproveChatJoinRequestPayload(chatId: String, userId: Long)

  /** Constructs minimal [[ApproveChatJoinRequest]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param userId
    *   Unique identifier of the target user
    * @return
    *   [[ApproveChatJoinRequest]] builder
    */
  def of(chatId: String, userId: Long): ApproveChatJoinRequest = ApproveChatJoinRequest(
    ApproveChatJoinRequestPayload(chatId = chatId, userId = userId)
  )
}

/** Use this method to approve a chat join request. The bot must be an administrator in the chat for this to work and
  * must have the can_invite_users administrator right. Returns True on success.
  */
case class ApproveChatJoinRequest(payload: ApproveChatJoinRequestPayload) extends Command[Boolean] {
  override val name: String = "approveChatJoinRequest"

  override def parameters: ApiParameters = JsonBody(payload)
}
