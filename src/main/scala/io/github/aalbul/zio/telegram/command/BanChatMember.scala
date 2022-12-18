package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.BanChatMember.BanChatMemberPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.codecs.boolean

import java.time.Instant

object BanChatMember {
  object BanChatMemberPayload {
    implicit val banChatMemberPayloadJsonCodec: JsonValueCodec[BanChatMemberPayload] = JsonCodecMaker.make(
      CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
    )
  }

  case class BanChatMemberPayload(
    chatId: String,
    userId: Long,
    untilDate: Option[Instant],
    revokeMessages: Option[Boolean]
  )

  /** Constructs minimal [[BanChatMember]] command
    * @param chatId
    *   Unique identifier for the target group or username of the target supergroup or channel (in the format
    *   \@channelusername)
    * @param userId
    *   Unique identifier of the target user
    *
    * @return
    *   [[BanChatMember]] builder
    */
  def of(chatId: String, userId: Long): BanChatMember = BanChatMember(
    BanChatMemberPayload(chatId = chatId, userId = userId, untilDate = None, revokeMessages = None)
  )
}

/** Use this method to ban a user in a group, a supergroup or a channel. In the case of supergroups and channels, the
  * user will not be able to return to the chat on their own using invite links, etc., unless
  * [[https://core.telegram.org/bots/api#unbanchatmember unbanned]] first. The bot must be an administrator in the chat
  * for this to work and must have the appropriate administrator rights. Returns ''True'' on success.
  */
case class BanChatMember(payload: BanChatMemberPayload) extends Command[Boolean] {
  override val name: String = "banChatMember"

  override def parameters: ApiParameters = JsonBody(payload)

  /** Date when the user will be unbanned, unix time. If user is banned for more than 366 days or less than 30 seconds
    * from the current time they are considered to be banned forever. Applied for supergroups and channels only.
    */
  def withUntilDate(date: Instant): BanChatMember = copy(payload = payload.copy(untilDate = Some(date)))

  /** Pass ''True'' to delete all messages from the chat for the user that is being removed. If False, the user will be
    * able to see messages in the group that were sent before the user was removed. Always ''True'' for supergroups and
    * channels.
    */
  def withRevokeMessages(revoke: Boolean): BanChatMember = copy(payload = payload.copy(revokeMessages = Some(revoke)))
}
