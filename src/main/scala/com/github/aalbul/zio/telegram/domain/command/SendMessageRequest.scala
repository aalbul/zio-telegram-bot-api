package com.github.aalbul.zio.telegram.domain.command

import com.github.aalbul.zio.telegram.domain.MessageEntity
import com.github.aalbul.zio.telegram.domain.ParseModes.ParseMode
import io.circe.generic.extras.ConfiguredJsonCodec

object SendMessageRequest {
  def forChat(chatId: String, text: String): SendMessageRequest = SendMessageRequest(
    chatId = chatId,
    text = text,
    parseMode = None,
    entities = None,
    disableWebPagePreview = None,
    disableNotification = None,
    protectContent = None,
    replyToMessageId = None,
    allowSendingWithoutReply = None
  )
}

@ConfiguredJsonCodec(encodeOnly = true)
case class SendMessageRequest(
  chatId: String,
  text: String,
  parseMode: Option[ParseMode],
  entities: Option[Seq[MessageEntity]],
  disableWebPagePreview: Option[Boolean],
  disableNotification: Option[Boolean],
  protectContent: Option[Boolean],
  replyToMessageId: Option[Long],
  allowSendingWithoutReply: Option[Boolean]
  // replyMarkup: Option[] TODO: Solve
) {
  def withParseMode(parseMode: ParseMode): SendMessageRequest = copy(parseMode = Some(parseMode))
  def withEntities(entities: Seq[MessageEntity]): SendMessageRequest = copy(entities = Some(entities))
  def withDisableWebPagePreview(disable: Boolean): SendMessageRequest = copy(disableWebPagePreview = Some(disable))
  def withDisableNotification(disable: Boolean): SendMessageRequest = copy(disableNotification = Some(disable))
  def withProtectContent(protect: Boolean): SendMessageRequest = copy(protectContent = Some(protect))
  def withReplyToMessageId(id: Long): SendMessageRequest = copy(replyToMessageId = Some(id))
  def withAllowSendingWithoutReply(allow: Boolean): SendMessageRequest = copy(allowSendingWithoutReply = Some(allow))
}
