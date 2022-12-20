package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import java.time.Instant

object ChatInviteLink {
  implicit val chatInviteLinkJsonCodec: JsonValueCodec[ChatInviteLink] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[ChatInviteLink]] object
    * @param inviteLink
    *   The invite link. If the link was created by another chat administrator, then the second part of the link will be
    *   replaced with “…”.
    * @param creator
    *   Creator of the link
    * @param createsJoinRequest
    *   True, if users joining the chat via the link need to be approved by chat administrators
    * @param isPrimary
    *   True, if the link is primary
    * @param isRevoked
    *   True, if the link is revoked
    * @return
    *   [[ChatInviteLink]] builder
    */
  def of(
    inviteLink: String,
    creator: User,
    createsJoinRequest: Boolean,
    isPrimary: Boolean,
    isRevoked: Boolean
  ): ChatInviteLink = ChatInviteLink(
    inviteLink = inviteLink,
    creator = creator,
    createsJoinRequest = createsJoinRequest,
    isPrimary = isPrimary,
    isRevoked = isRevoked,
    name = None,
    expirationDate = None,
    memberLimit = None,
    pendingJoinRequestCount = None
  )
}

/** Represents an invite link for a chat.
  */
case class ChatInviteLink(
  inviteLink: String,
  creator: User,
  createsJoinRequest: Boolean,
  isPrimary: Boolean,
  isRevoked: Boolean,
  name: Option[String],
  expirationDate: Option[Instant],
  memberLimit: Option[Int],
  pendingJoinRequestCount: Option[Long]
) {

  /** Invite link name
    */
  def withName(name: String): ChatInviteLink = copy(name = Some(name))

  /** Point in time (Unix timestamp) when the link will expire or has been expired
    */
  def withExpirationDate(expirationDate: Instant): ChatInviteLink = copy(expirationDate = Some(expirationDate))

  /** The maximum number of users that can be members of the chat simultaneously after joining the chat via this invite
    * link; 1-99999
    */
  def withMemberLimit(memberLimit: Int): ChatInviteLink = copy(memberLimit = Some(memberLimit))

  /** Number of pending join requests created using this link
    */
  def withPendingJoinRequestCount(pendingJoinRequestCount: Long): ChatInviteLink =
    copy(pendingJoinRequestCount = Some(pendingJoinRequestCount))
}
