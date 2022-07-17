package io.github.aalbul.zio.telegram.domain

import io.circe.Encoder
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.StringOps

object UpdateTypes extends Enumeration {
  implicit val updateTypeEncoder: Encoder[UpdateType] = Encoder.encodeString.contramap(_.toString.camelToSnakeCase)
  type UpdateType = Value

  val Message, EditedMessage, ChannelPost, EditedChannelPost, InlineQuery, ChosenInlineResult, CallbackQuery,
    ShippingQuery, PreCheckoutQuery, Poll, PollAnswer, MyChatMember, ChatMember, ChatJoinRequest = Value
}
