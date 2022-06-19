package io.github.aalbul.zio.telegram.command

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.command.SendMessage.SendMessagePayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.ParseModes.ParseMode
import io.github.aalbul.zio.telegram.domain.{Markup, Message, MessageEntity}

object SendMessage {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class SendMessagePayload(
    chatId: String,
    text: String,
    parseMode: Option[ParseMode],
    entities: Option[Seq[MessageEntity]],
    disableWebPagePreview: Option[Boolean],
    disableNotification: Option[Boolean],
    protectContent: Option[Boolean],
    replyToMessageId: Option[Long],
    allowSendingWithoutReply: Option[Boolean],
    replyMarkup: Option[Markup]
  )

  /** Constructs minimal [[SendMessage]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param text
    *   Text of the message to be sent, '''1-4096''' characters after entities parsing
    * @return
    *   [[SendMessage]] builder
    */
  def of(chatId: String, text: String): SendMessage = SendMessage(
    SendMessagePayload(
      chatId = chatId,
      text = text,
      parseMode = None,
      entities = None,
      disableWebPagePreview = None,
      disableNotification = None,
      protectContent = None,
      replyToMessageId = None,
      allowSendingWithoutReply = None,
      replyMarkup = None
    )
  )
}

/** Use this method to send text messages. On success, the sent [[Message]] is returned.
  */
case class SendMessage(payload: SendMessagePayload) extends Command[Message] {
  override val name: String = "sendMessage"

  override def parameters: ApiParameters = JsonBody(payload)

  /** Mode for parsing entities in the message text. See [[https://core.telegram.org/bots/api#formatting-options]] for
    * more details.
    */
  def withParseMode(parseMode: ParseMode): SendMessage = copy(payload.copy(parseMode = Some(parseMode)))

  /** A list of special entities that appear in message text, which can be specified instead of parse_mode */
  def withEntities(entities: Seq[MessageEntity]): SendMessage = copy(payload.copy(entities = Some(entities)))

  /** Disables link previews for links in this message */
  def withDisableWebPagePreview(disable: Boolean): SendMessage = copy(
    payload.copy(disableWebPagePreview = Some(disable))
  )

  /** Sends the message silently. Users will receive a notification with no sound. */
  def withDisableNotification(disable: Boolean): SendMessage = copy(payload.copy(disableNotification = Some(disable)))

  /** Protects the contents of the sent message from forwarding and saving */
  def withProtectContent(protect: Boolean): SendMessage = copy(payload.copy(protectContent = Some(protect)))

  /** If the message is a reply, ID of the original message */
  def withReplyToMessageId(id: Long): SendMessage = copy(payload.copy(replyToMessageId = Some(id)))

  /** Pass ''True'', if the message should be sent even if the specified replied-to message is not found */
  def withAllowSendingWithoutReply(allow: Boolean): SendMessage = copy(
    payload.copy(allowSendingWithoutReply = Some(allow))
  )

  /** Additional interface options. An object for an
    * [[https://core.telegram.org/bots#inline-keyboards-and-on-the-fly-updating inline keyboard]],
    * [[https://core.telegram.org/bots#keyboards custom reply keyboard]], instructions to remove reply keyboard or to
    * force a reply from the user.
    */
  def withReplyMarkup(markup: Markup): SendMessage = copy(payload.copy(replyMarkup = Some(markup)))
}
