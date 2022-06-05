package com.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
case class Update(
  updateId: Int,
  message: Option[Message],
  editedMessage: Option[Message],
  channelPost: Option[Message],
  editedChannelPost: Option[Message],
  inlineQuery: Option[InlineQuery],
  chosenInlineResult: Option[ChosenInlineResult],
  callbackQuery: Option[CallbackQuery],
  shippingQuery: Option[ShippingQuery],
  preCheckoutQuery: Option[PreCheckoutQuery],
  poll: Option[Poll],
  pollAnswer: Option[PollAnswer],
  myChatMember: Option[ChatMemberUpdated],
  chatMember: Option[ChatMemberUpdated],
  chatJoinRequest: Option[ChatJoinRequest]
)
