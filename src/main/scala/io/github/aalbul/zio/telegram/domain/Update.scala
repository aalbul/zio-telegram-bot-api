package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

object Update {
  def of(updateId: Int): Update = Update(
    updateId = updateId,
    message = None,
    editedMessage = None,
    channelPost = None,
    editedChannelPost = None,
    inlineQuery = None,
    chosenInlineResult = None,
    callbackQuery = None,
    shippingQuery = None,
    preCheckoutQuery = None,
    poll = None,
    pollAnswer = None,
    myChatMember = None,
    chatMember = None,
    chatJoinRequest = None
  )
}

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
