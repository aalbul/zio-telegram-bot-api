package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.SendContact.SendContactPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.{Markup, Message}

object SendContact {
  object SendContactPayload {
    implicit val sendContactPayloadJsonCodec: JsonValueCodec[SendContactPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class SendContactPayload(
    chatId: String,
    messageThreadId: Option[Long],
    phoneNumber: String,
    firstName: String,
    lastName: Option[String],
    vcard: Option[String],
    disableNotification: Option[Boolean],
    protectContent: Option[Boolean],
    replyToMessageId: Option[Long],
    allowSendingWithoutReply: Option[Boolean],
    replyMarkup: Option[Markup]
  )

  /** Constructs minimal [[SendContact]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param phoneNumber
    *   Contact's phone number
    * @param firstName
    *   Contact's first name
    * @return
    *   [[SendContact]] builder
    */
  def of(chatId: String, phoneNumber: String, firstName: String): SendContact = SendContact(
    SendContactPayload(
      chatId = chatId,
      messageThreadId = None,
      phoneNumber = phoneNumber,
      firstName = firstName,
      lastName = None,
      vcard = None,
      disableNotification = None,
      protectContent = None,
      replyToMessageId = None,
      allowSendingWithoutReply = None,
      replyMarkup = None
    )
  )
}

/** Use this method to send phone contacts. On success, the sent [[Message]] is returned.
  */
case class SendContact(payload: SendContactPayload) extends Command[Message] {
  override val name: String = "sendContact"

  override def parameters: ApiParameters = JsonBody(payload)

  /** Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
    */
  def withMessageThreadId(messageThreadId: Long): SendContact = copy(
    payload.copy(messageThreadId = Some(messageThreadId))
  )

  /** Contact's last name
    */
  def withLastName(lastName: String): SendContact = copy(payload.copy(lastName = Some(lastName)))

  /** Additional data about the contact in the form of a [[https://en.wikipedia.org/wiki/VCard vCard]], 0-2048 bytes
    */
  def withVcard(vcard: String): SendContact = copy(payload.copy(vcard = Some(vcard)))

  /** Sends the message [[https://telegram.org/blog/channels-2-0#silent-messages silently]]. Users will receive a
    * notification with no sound.
    */
  def withDisableNotification(disable: Boolean): SendContact = copy(payload.copy(disableNotification = Some(disable)))

  /** Protects the contents of the sent message from forwarding and saving
    */
  def withProtectContent(protect: Boolean): SendContact = copy(payload.copy(protectContent = Some(protect)))

  /** If the message is a reply, ID of the original message
    */
  def withReplyToMessageId(id: Long): SendContact = copy(payload.copy(replyToMessageId = Some(id)))

  /** Pass ''True'', if the message should be sent even if the specified replied-to message is not found
    */
  def withAllowSendingWithoutReply(allow: Boolean): SendContact = copy(
    payload.copy(allowSendingWithoutReply = Some(allow))
  )

  /** Additional interface options. An object for an
    * [[https://core.telegram.org/bots#inline-keyboards-and-on-the-fly-updating inline keyboard]],
    * [[https://core.telegram.org/bots#keyboards custom reply keyboard]], instructions to remove reply keyboard or to
    * force a reply from the user.
    */
  def withReplyMarkup(markup: Markup): SendContact = copy(payload.copy(replyMarkup = Some(markup)))
}
