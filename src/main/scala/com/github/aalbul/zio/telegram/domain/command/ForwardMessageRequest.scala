package com.github.aalbul.zio.telegram.domain.command

import io.circe.generic.extras.ConfiguredJsonCodec

object ForwardMessageRequest {
  def forMessage(messageId: Long, fromChatId: String, toChatId: String): ForwardMessageRequest = ForwardMessageRequest(
    chatId = toChatId,
    fromChatId = fromChatId,
    messageId = messageId,
    disableNotification = None,
    protectContent = None
  )
}

@ConfiguredJsonCodec(encodeOnly = true)
case class ForwardMessageRequest(
  chatId: String,
  fromChatId: String,
  messageId: Long,
  disableNotification: Option[Boolean],
  protectContent: Option[Boolean]
) {
  def withDisableNotification(disable: Boolean): ForwardMessageRequest = copy(disableNotification = Some(disable))
  def withProtectContent(protect: Boolean): ForwardMessageRequest = copy(protectContent = Some(protect))
}
