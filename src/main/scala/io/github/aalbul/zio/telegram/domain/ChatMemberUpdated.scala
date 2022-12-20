package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import java.time.Instant

object ChatMemberUpdated {
  implicit val chatMemberUpdatedJsonCodec: JsonValueCodec[ChatMemberUpdated] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[ChatMemberUpdated]] object
    * @param chat
    *   Chat the user belongs to
    * @param from
    *   Performer of the action, which resulted in the change
    * @param date
    *   Date the change was done in Unix time
    * @param oldChatMember
    *   Previous information about the chat member
    * @param newChatMember
    *   New information about the chat member
    * @return
    *   [[ChatMemberUpdated]] builder
    */
  def of(
    chat: Chat,
    from: User,
    date: Instant,
    oldChatMember: ChatMember,
    newChatMember: ChatMember
  ): ChatMemberUpdated = ChatMemberUpdated(
    chat = chat,
    from = from,
    date = date,
    oldChatMember = oldChatMember,
    newChatMember = newChatMember,
    inviteLink = None
  )
}

/** This object represents changes in the status of a chat member.
  */
case class ChatMemberUpdated(
  chat: Chat,
  from: User,
  date: Instant,
  oldChatMember: ChatMember,
  newChatMember: ChatMember,
  inviteLink: Option[ChatInviteLink]
) {

  /** Chat invite link, which was used by the user to join the chat; for joining by invite link events only.
    */
  def withInviteLink(inviteLink: ChatInviteLink): ChatMemberUpdated = copy(inviteLink = Some(inviteLink))
}
