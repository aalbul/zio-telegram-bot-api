package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object VideoChatParticipantsInvited {

  /** Constructs minimal [[VideoChatParticipantsInvited]]
    * @param users
    *   New members that were invited to the video chat
    * @return
    *   [[VideoChatParticipantsInvited]] builder
    */
  def of(users: Seq[User]): VideoChatParticipantsInvited = VideoChatParticipantsInvited(
    users = users
  )

  implicit val videoChatParticipantsInvitedJsonCodec: JsonValueCodec[VideoChatParticipantsInvited] =
    JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
}

/** This object represents a service message about new members invited to a video chat.
  */
case class VideoChatParticipantsInvited(users: Seq[User])
