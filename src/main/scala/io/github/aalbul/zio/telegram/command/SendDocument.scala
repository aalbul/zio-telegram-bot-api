package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.ParseModes.ParseMode
import io.github.aalbul.zio.telegram.domain.{Markup, Message, MessageEntity}

object SendDocument {
  def of(chatId: String, document: FileDescriptor): SendDocument = new SendDocument(
    chatId = chatId,
    document = document,
    thumb = None,
    caption = None,
    parseMode = None,
    captionEntities = None,
    disableContentTypeDetection = None,
    disableNotification = None,
    protectContent = None,
    replyToMessageId = None,
    allowSendingWithoutReply = None,
    replyMarkup = None
  )
}

case class SendDocument(
  chatId: String,
  document: FileDescriptor,
  thumb: Option[FileDescriptor],
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  disableContentTypeDetection: Option[Boolean],
  disableNotification: Option[Boolean],
  protectContent: Option[Boolean],
  replyToMessageId: Option[Long],
  allowSendingWithoutReply: Option[Boolean],
  replyMarkup: Option[Markup]
) extends Command[Message] {
  override val name: String = "sendDocument"

  override def parameters: ApiParameters = MultipartBody.ofOpt(
    Some(stringPart("chat_id", chatId)),
    Some(document.asMultipart("document")),
    thumb.map(_.asMultipart("thumb")),
    caption.map(stringPart("caption", _)),
    parseMode.map(mode => stringPart("parse_mode", mode.toString)),
    captionEntities.map(entities => stringPart("caption_entities", JsonBody(entities).toJson)),
    disableContentTypeDetection.map(stringPart("disable_content_type_detection", _)),
    disableNotification.map(stringPart("disable_notification", _)),
    protectContent.map(stringPart("protect_content", _)),
    replyToMessageId.map(stringPart("reply_to_message_id", _)),
    allowSendingWithoutReply.map(stringPart("allow_sending_without_reply", _)),
    replyMarkup.map(markup => stringPart("reply_markup", JsonBody(markup).toJson))
  )

  def withThumb(thumb: FileDescriptor): SendDocument = copy(thumb = Some(thumb))
  def withCaption(caption: String): SendDocument = copy(caption = Some(caption))
  def withParseMode(parseMode: ParseMode): SendDocument = copy(parseMode = Some(parseMode))
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): SendDocument =
    copy(captionEntities = Some(captionEntities))
  def withDisableContentTypeDetection(disable: Boolean): SendDocument =
    copy(disableContentTypeDetection = Some(disable))
  def withDisableNotification(disable: Boolean): SendDocument = copy(disableNotification = Some(disable))
  def withProtectContent(protect: Boolean): SendDocument = copy(protectContent = Some(protect))
  def withReplyToMessageId(id: Int): SendDocument = copy(replyToMessageId = Some(id))
  def withAllowSendingWithoutReply(allow: Boolean): SendDocument = copy(allowSendingWithoutReply = Some(allow))
  def withReplyMarkup(markup: Markup): SendDocument = copy(replyMarkup = Some(markup))
}
