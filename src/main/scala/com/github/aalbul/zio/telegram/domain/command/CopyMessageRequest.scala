package com.github.aalbul.zio.telegram.domain.command

import com.github.aalbul.zio.telegram.domain.ParseModes.ParseMode
import com.github.aalbul.zio.telegram.domain.{Markup, MessageEntity}
import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(encodeOnly = true)
case class CopyMessageRequest(
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
