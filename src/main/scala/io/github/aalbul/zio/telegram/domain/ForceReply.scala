package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ForceReply {
  implicit val forceReplyJsonCodec: JsonValueCodec[ForceReply] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[ForceReply]] object
    * @param forceReply
    *   Shows reply interface to the user, as if they manually selected the bot's message and tapped 'Reply'
    * @return
    *   [[ForceReply]] builder
    */
  def of(forceReply: Boolean): ForceReply = ForceReply(
    forceReply = forceReply,
    inputFieldPlaceholder = None,
    selective = None
  )
}

/** Upon receiving a message with this object, Telegram clients will display a reply interface to the user (act as if
  * the user has selected the bot's message and tapped 'Reply'). This can be extremely useful if you want to create
  * user-friendly step-by-step interfaces without having to sacrifice
  * [[https://core.telegram.org/bots/features#privacy-mode privacy mode]].
  */
case class ForceReply(forceReply: Boolean, inputFieldPlaceholder: Option[String], selective: Option[Boolean])
  extends Markup {

  /** The placeholder to be shown in the input field when the reply is active; 1-64 characters
    */
  def withInputFieldPlaceholder(inputFieldPlaceholder: String): ForceReply =
    copy(inputFieldPlaceholder = Some(inputFieldPlaceholder))

  /** Use this parameter if you want to force reply from specific users only. Targets: 1) users that are @mentioned in
    * the text of the [[https://core.telegram.org/bots/api#message Message]] object; 2) if the bot's message is a reply
    * (has reply_to_message_id), sender of the original message.
    */
  def withSelective(selective: Boolean): ForceReply = copy(selective = Some(selective))
}
