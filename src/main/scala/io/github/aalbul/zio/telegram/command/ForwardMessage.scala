package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.ForwardMessage.ForwardMessagePayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.Message

object ForwardMessage {
  object ForwardMessagePayload {
    implicit val forwardMessagePayloadJsonCodec: JsonValueCodec[ForwardMessagePayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class ForwardMessagePayload(
    chatId: String,
    messageThreadId: Option[Long],
    fromChatId: String,
    messageId: Long,
    disableNotification: Option[Boolean],
    protectContent: Option[Boolean]
  )

  /** Constructs minimal [[ForwardMessage]] command
    * @param messageId
    *   Message identifier in the chat specified in `fromChatId`
    * @param fromChatId
    *   Unique identifier for the chat where the original message was sent (or channel username in the format
    *   \@channelusername)
    * @param toChatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @return
    *   [[ForwardMessage]] builder
    */
  def of(messageId: Long, fromChatId: String, toChatId: String): ForwardMessage = ForwardMessage(
    ForwardMessagePayload(
      chatId = toChatId,
      messageThreadId = None,
      fromChatId = fromChatId,
      messageId = messageId,
      disableNotification = None,
      protectContent = None
    )
  )
}

/** Use this method to forward messages of any kind. Service messages can't be forwarded. On success, the sent
  * [[Message]] is returned.
  */
case class ForwardMessage(payload: ForwardMessagePayload) extends Command[Message] {
  override val name: String = "forwardMessage"
  override def parameters: ApiParameters = JsonBody(payload)

  /** Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
    */
  def withMessageThreadId(messageThreadId: Long): ForwardMessage = copy(
    payload.copy(messageThreadId = Some(messageThreadId))
  )

  /** Sends the message [[https://telegram.org/blog/channels-2-0#silent-messages silently]]. Users will receive a
    * notification with no sound.
    */
  def withDisableNotification(disable: Boolean): ForwardMessage = copy(
    payload.copy(disableNotification = Some(disable))
  )

  /** Protects the contents of the forwarded message from forwarding and saving
    */
  def withProtectContent(protect: Boolean): ForwardMessage = copy(payload.copy(protectContent = Some(protect)))
}
