package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.EditChatInviteLink.EditChatInviteLinkPayload
import io.github.aalbul.zio.telegram.domain.ChatInviteLink
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import java.time.Instant

object EditChatInviteLink {
  object EditChatInviteLinkPayload {
    implicit val editChatInviteLinkPayloadJsonCodec: JsonValueCodec[EditChatInviteLinkPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class EditChatInviteLinkPayload(
    chatId: String,
    inviteLink: String,
    name: Option[String],
    expireDate: Option[Instant],
    memberLimit: Option[Long],
    createsJoinRequest: Option[Boolean]
  )

  /** Constructs minimal [[EditChatInviteLink]] command
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param inviteLink
    *   The invite link to edit
    * @return
    *   [[EditChatInviteLink]] builder
    */
  def of(chatId: String, inviteLink: String): EditChatInviteLink = EditChatInviteLink(
    EditChatInviteLinkPayload(
      chatId = chatId,
      inviteLink = inviteLink,
      name = None,
      expireDate = None,
      memberLimit = None,
      createsJoinRequest = None
    )
  )
}

/** Use this method to edit a non-primary invite link created by the bot. The bot must be an administrator in the chat
  * for this to work and must have the appropriate administrator rights. Returns the edited invite link as a
  * [[https://core.telegram.org/bots/api#chatinvitelink ChatInviteLink]] object.
  */
case class EditChatInviteLink(payload: EditChatInviteLinkPayload) extends Command[ChatInviteLink] {
  override val name: String = "editChatInviteLink"

  override def parameters: ApiParameters = JsonBody(payload)

  /** Invite link name; 0-32 characters
    */
  def withName(name: String): EditChatInviteLink = copy(payload.copy(name = Some(name)))

  /** Point in time (Unix timestamp) when the link will expire
    */
  def withExpireDate(expireDate: Instant): EditChatInviteLink = copy(payload.copy(expireDate = Some(expireDate)))

  /** The maximum number of users that can be members of the chat simultaneously after joining the chat via this invite
    * link; 1-99999
    */
  def withMemberLimit(memberLimit: Long): EditChatInviteLink = copy(payload.copy(memberLimit = Some(memberLimit)))

  /** True, if users joining the chat via the link need to be approved by chat administrators. If True, member_limit
    * can't be specified
    */
  def withCreatesJoinRequest(createsJoinRequest: Boolean): EditChatInviteLink = copy(
    payload.copy(createsJoinRequest = Some(createsJoinRequest))
  )
}
