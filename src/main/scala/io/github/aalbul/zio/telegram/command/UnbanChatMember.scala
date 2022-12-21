package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.UnbanChatMember.UnbanChatMemberPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object UnbanChatMember {
  object UnbanChatMemberPayload {
    implicit val unbanChatMemberPayloadJsonCodec: JsonValueCodec[UnbanChatMemberPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class UnbanChatMemberPayload(chatId: String, userId: Long, onlyIfBanned: Option[Boolean])

  /** Constructs minimal [[UnbanChatMember]] command
    * @param chatId
    *   Unique identifier for the target group or username of the target supergroup or channel (in the format
    *   \@channelusername)
    * @param userId
    *   Unique identifier of the target user
    * @return
    *   [[UnbanChatMember]] builder
    */
  def of(chatId: String, userId: Long): UnbanChatMember = UnbanChatMember(
    UnbanChatMemberPayload(
      chatId = chatId,
      userId = userId,
      onlyIfBanned = None
    )
  )
}

/** Use this method to unban a previously banned user in a supergroup or channel. The user will not return to the group
  * or channel automatically, but will be able to join via link, etc. The bot must be an administrator for this to work.
  * By default, this method guarantees that after the call the user is not a member of the chat, but will be able to
  * join it. So if the user is a member of the chat they will also be removed from the chat. If you don't want this, use
  * the parameter ''only_if_banned''. Returns ''True'' on success.
  */
case class UnbanChatMember(payload: UnbanChatMemberPayload) extends Command[Boolean] {
  override val name: String = "unbanChatMember"

  override def parameters: ApiParameters = JsonBody(payload)

  /** Do nothing if the user is not banned */
  def withOnlyIfBanned(only: Boolean): UnbanChatMember = copy(payload = payload.copy(onlyIfBanned = Some(only)))
}
