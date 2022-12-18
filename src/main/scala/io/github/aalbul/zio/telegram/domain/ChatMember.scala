package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{named, CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ChatMember {
  implicit val chatMemberJsonCodec: JsonValueCodec[ChatMember] = JsonCodecMaker.make(
    CodecMakerConfig
      .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
      .withDiscriminatorFieldName(Some("status"))
  )
}

sealed trait ChatMember

@named("administrator")
case class ChatMemberAdministrator(
  user: User,
  canBeEdited: Boolean,
  isAnonymous: Boolean,
  canManageChat: Boolean,
  canDeleteMessages: Boolean,
  canManageVideoChats: Boolean,
  canRestrictMembers: Boolean,
  canPromoteMembers: Boolean,
  canChangeInfo: Boolean,
  canInviteUsers: Boolean,
  canPostMessages: Option[Boolean],
  canEditMessages: Option[Boolean],
  canPinMessages: Option[Boolean],
  canManageTopics: Option[Boolean],
  customerTitle: Option[String]
) extends ChatMember

@named("kicked")
case class ChatMemberBanned(user: User, untilDate: Int) extends ChatMember

@named("left")
case class ChatMemberLeft(user: User) extends ChatMember

@named("member")
case class ChatMemberMember(user: User) extends ChatMember

@named("creator")
case class ChatMemberOwner(user: User, isAnonymous: Boolean, customTitle: Option[String]) extends ChatMember

@named("restricted")
case class ChatMemberRestricted(
  user: User,
  isMember: Boolean,
  canChangeInfo: Boolean,
  canInviteUsers: Boolean,
  canPinMembers: Boolean,
  canSendMessages: Boolean,
  canSendMediaMessages: Boolean,
  canSendPolls: Boolean,
  canSendOtherMessages: Boolean,
  canAddWebPagePreviews: Boolean,
  untilDate: Int
) extends ChatMember
