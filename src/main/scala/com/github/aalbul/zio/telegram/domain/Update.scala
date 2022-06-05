package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.CallbackQuery.CallbackQueryOps
import com.github.aalbul.zio.telegram.domain.ChatJoinRequest.ChatJoinRequestOps
import com.github.aalbul.zio.telegram.domain.ChatMemberUpdated.ChatMemberUpdatedOps
import com.github.aalbul.zio.telegram.domain.ChosenInlineResult.ChosenInlineResultOps
import com.github.aalbul.zio.telegram.domain.InlineQuery.InlineQueryOps
import com.github.aalbul.zio.telegram.domain.Message.MessageOps
import com.github.aalbul.zio.telegram.domain.Poll.PollOps
import com.github.aalbul.zio.telegram.domain.PollAnswer.PollAnswerOps
import com.github.aalbul.zio.telegram.domain.PreCheckoutQuery.PreCheckoutQueryOps
import com.github.aalbul.zio.telegram.domain.ShippingQuery.ShippingQueryOps
import com.pengrad.telegrambot.model.Update as LibUpdate

object Update {
  implicit class UpdateOps(update: LibUpdate) {
    def asScala: Update = Update(
      updateId = update.updateId(),
      message = Option(update.message()).map(_.asScala),
      editedMessage = Option(update.editedMessage()).map(_.asScala),
      channelPost = Option(update.channelPost()).map(_.asScala),
      editedChannelPost = Option(update.editedChannelPost()).map(_.asScala),
      inlineQuery = Option(update.inlineQuery()).map(_.asScala),
      chosenInlineResult = Option(update.chosenInlineResult()).map(_.asScala),
      callbackQuery = Option(update.callbackQuery()).map(_.asScala),
      shippingQuery = Option(update.shippingQuery()).map(_.asScala),
      preCheckoutQuery = Option(update.preCheckoutQuery()).map(_.asScala),
      poll = Option(update.poll()).map(_.asScala),
      pollAnswer = Option(update.pollAnswer()).map(_.asScala),
      myChatMember = Option(update.myChatMember()).map(_.asScala),
      chatMember = Option(update.chatMember()).map(_.asScala),
      chatJoinRequest = Option(update.chatJoinRequest()).map(_.asScala)
    )
  }
}

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
