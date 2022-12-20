package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import java.time.Instant

object ChatJoinRequest {
  implicit val chatJoinRequestJsonObject: JsonValueCodec[ChatJoinRequest] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[ChatJoinRequest]] object
    * @param chat
    *   Chat to which the request was sent
    * @param from
    *   User that sent the join request
    * @param date
    *   Date the request was sent in Unix time
    * @return
    *   [[ChatJoinRequest]] builder
    */
  def of(chat: Chat, from: User, date: Instant): ChatJoinRequest = ChatJoinRequest(
    chat = chat,
    from = from,
    date = date,
    bio = None,
    inviteLink = None
  )
}

/** Represents a join request sent to a chat.
  */
case class ChatJoinRequest(
  chat: Chat,
  from: User,
  date: Instant,
  bio: Option[String],
  inviteLink: Option[ChatInviteLink]
) {

  /** Bio of the user
    */
  def withBio(bio: String): ChatJoinRequest = copy(bio = Some(bio))

  /** Chat invite link that was used by the user to send the join request
    */
  def withInviteLink(inviteLink: ChatInviteLink): ChatJoinRequest = copy(inviteLink = Some(inviteLink))
}
