package com.github.aalbul.zio.telegram.command

import com.github.aalbul.zio.telegram.command.SendContact.SendContactPayload
import com.github.aalbul.zio.telegram.domain.{Markup, Message}
import io.circe.generic.extras.ConfiguredJsonCodec

object SendContact {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class SendContactPayload(
    chatId: String,
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

  def of(chatId: String, phoneNumber: String, firstName: String): SendContact = SendContact(
    SendContactPayload(
      chatId = chatId,
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

case class SendContact(payload: SendContactPayload) extends Command[Message] {
  override val name: String = "sendContact"

  override def parameters: ApiParameters = JsonBody(payload)

  def withLastName(lastName: String): SendContact = copy(payload.copy(lastName = Some(lastName)))
  def withVcard(vcard: String): SendContact = copy(payload.copy(vcard = Some(vcard)))
  def withDisableNotification(disable: Boolean): SendContact = copy(payload.copy(disableNotification = Some(disable)))
  def withProtectContent(protect: Boolean): SendContact = copy(payload.copy(protectContent = Some(protect)))
  def withReplyToMessageId(id: Long): SendContact = copy(payload.copy(replyToMessageId = Some(id)))
  def withAllowSendingWithoutReply(allow: Boolean): SendContact = copy(
    payload.copy(allowSendingWithoutReply = Some(allow))
  )
  def withReplyMarkup(markup: Markup): SendContact = copy(payload.copy(replyMarkup = Some(markup)))
}
