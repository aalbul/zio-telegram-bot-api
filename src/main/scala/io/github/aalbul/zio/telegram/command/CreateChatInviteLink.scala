package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.CreateChatInviteLink.CreateChatInviteLinkPayload
import io.github.aalbul.zio.telegram.domain.ChatInviteLink
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import java.time.Instant

object CreateChatInviteLink {
  object CreateChatInviteLinkPayload {
    implicit val createChatInviteLinkPayloadJsonCodec: JsonValueCodec[CreateChatInviteLinkPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class CreateChatInviteLinkPayload(
    chatId: String,
    name: Option[String],
    expireDate: Option[Instant],
    memberLimit: Option[Long],
    createsJoinRequest: Option[Boolean]
  )

  /** Constructs minimal [[CreateChatInviteLink]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @return
    *   [[CreateChatInviteLink]] builder
    */
  def of(chatId: String): CreateChatInviteLink = CreateChatInviteLink(
    CreateChatInviteLinkPayload(
      chatId = chatId,
      name = None,
      expireDate = None,
      memberLimit = None,
      createsJoinRequest = None
    )
  )
}

/** Use this method to create an additional invite link for a chat. The bot must be an administrator in the chat for
  * this to work and must have the appropriate administrator rights. The link can be revoked using the method
  * [[https://core.telegram.org/bots/api#revokechatinvitelink revokeChatInviteLink]]. Returns the new invite link as
  * [[https://core.telegram.org/bots/api#chatinvitelink ChatInviteLink]] object.
  */
case class CreateChatInviteLink(payload: CreateChatInviteLinkPayload) extends Command[ChatInviteLink] {
  override val name: String = "createChatInviteLink"

  override def parameters: ApiParameters = JsonBody(payload)

  /** Invite link name; 0-32 characters
    */
  def withName(name: String): CreateChatInviteLink = copy(payload.copy(name = Some(name)))

  /** Point in time (Unix timestamp) when the link will expire
    */
  def withExpireDate(expireDate: Instant): CreateChatInviteLink = copy(payload.copy(expireDate = Some(expireDate)))

  /** The maximum number of users that can be members of the chat simultaneously after joining the chat via this invite
    * link; 1-99999
    */
  def withMemberLimit(memberLimit: Long): CreateChatInviteLink = copy(payload.copy(memberLimit = Some(memberLimit)))

  /** True, if users joining the chat via the link need to be approved by chat administrators. If True, member_limit
    * can't be specified
    */
  def withCreatesJoinRequest(createsJoinRequest: Boolean): CreateChatInviteLink = copy(
    payload.copy(createsJoinRequest = Some(createsJoinRequest))
  )
}
