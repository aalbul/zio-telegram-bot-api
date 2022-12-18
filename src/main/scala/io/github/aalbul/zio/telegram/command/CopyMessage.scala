package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.CopyMessage.CopyMessagePayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.{Markup, MessageEntity, MessageId, ParseMode}

object CopyMessage {
  object CopyMessagePayload {
    implicit val copyMessagePayloadJsonCodec: JsonValueCodec[CopyMessagePayload] = JsonCodecMaker.make(
      CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
    )
  }

  case class CopyMessagePayload(
    chatId: String,
    fromChatId: String,
    messageId: String,
    caption: Option[String],
    parseMode: Option[ParseMode],
    captionEntities: Option[Seq[MessageEntity]],
    disableNotification: Option[Boolean],
    protectContent: Option[Boolean],
    replyToMessageId: Option[Long],
    allowSendingWithoutReply: Option[Boolean],
    replyMarkup: Option[Markup]
  )

  /** Constructs minimal [[CopyMessage]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param fromChatId
    *   Unique identifier for the chat where the original message was sent (or channel username in the format
    *   \@channelusername)
    * @param messageId
    *   Message identifier in the chat specified in ''from_chat_id''
    * @return
    *   [[CopyMessage]] builder
    */
  def of(chatId: String, fromChatId: String, messageId: String): CopyMessage = CopyMessage(
    CopyMessagePayload(
      chatId = chatId,
      fromChatId = fromChatId,
      messageId = messageId,
      caption = None,
      parseMode = None,
      captionEntities = None,
      disableNotification = None,
      protectContent = None,
      replyToMessageId = None,
      allowSendingWithoutReply = None,
      replyMarkup = None
    )
  )
}

/** Use this method to copy messages of any kind. Service messages and invoice messages can't be copied. The method is
  * analogous to the method [[ForwardMessage]], but the copied message doesn't have a link to the original message.
  * Returns the `MessageId` of the sent message on success.
  */
case class CopyMessage(payload: CopyMessagePayload) extends Command[MessageId] {
  override val name: String = "copyMessage"
  override def parameters: ApiParameters = JsonBody(payload)

  /** New caption for media, 0-1024 characters after entities parsing. If not specified, the original caption is kept
    */
  def withCaption(caption: String): CopyMessage = copy(payload.copy(caption = Some(caption)))

  /** Mode for parsing entities in the new caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): CopyMessage = copy(payload.copy(parseMode = Some(parseMode)))

  /** A JSON-serialized list of special entities that appear in the new caption, which can be specified instead of
    * ''parse_mode''
    */
  def withCaptionEntities(entities: Seq[MessageEntity]): CopyMessage = copy(
    payload.copy(captionEntities = Some(entities))
  )

  /** Sends the message [[https://telegram.org/blog/channels-2-0#silent-messages silently]]. Users will receive a
    * notification with no sound.
    */
  def withDisableNotification(disable: Boolean): CopyMessage = copy(payload.copy(disableNotification = Some(disable)))

  /** Protects the contents of the sent message from forwarding and saving
    */
  def withProtectContent(protect: Boolean): CopyMessage = copy(payload.copy(protectContent = Some(protect)))

  /** If the message is a reply, ID of the original message
    */
  def withReplyToMessageId(id: Long): CopyMessage = copy(payload.copy(replyToMessageId = Some(id)))

  /** Pass ''True'', if the message should be sent even if the specified replied-to message is not found
    */
  def withAllowSendingWithoutReply(allow: Boolean): CopyMessage = copy(
    payload.copy(allowSendingWithoutReply = Some(allow))
  )

  /** Additional interface options. An object for an
    * [[https://core.telegram.org/bots#inline-keyboards-and-on-the-fly-updating inline keyboard]],
    * [[https://core.telegram.org/bots#keyboards custom reply keyboard]], instructions to remove reply keyboard or to
    * force a reply from the user.
    */
  def withReplyMarkup(markup: Markup): CopyMessage = copy(payload.copy(replyMarkup = Some(markup)))
}
