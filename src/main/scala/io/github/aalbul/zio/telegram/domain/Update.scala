package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object Update {
  implicit val updateJsonCodec: JsonValueCodec[Update] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  implicit val seqUpdateJsonCodec: JsonValueCodec[Seq[Update]] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[Update]]
    * @param updateId
    *   The update's unique identifier. Update identifiers start from a certain positive number and increase
    *   sequentially. This ID becomes especially handy if you're using
    *   [[https://core.telegram.org/bots/api#setwebhook webhooks]], since it allows you to ignore repeated updates or to
    *   restore the correct update sequence, should they get out of order. If there are no new updates for at least a
    *   week, then identifier of the next update will be chosen randomly instead of sequentially.
    * @return
    *   [[Update]] builder
    */
  def of(updateId: Long): Update = Update(
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

/** This object represents an incoming update. At most one of the optional parameters can be present in any given
  * update.
  */
case class Update(
  updateId: Long,
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
) {

  /** New incoming message of any kind - text, photo, sticker, etc. */
  def withMessage(message: Message): Update = copy(message = Some(message))

  /** New version of a message that is known to the bot and was edited */
  def withEditedMessage(message: Message): Update = copy(editedMessage = Some(message))

  /** New incoming channel post of any kind - text, photo, sticker, etc. */
  def withChannelPost(message: Message): Update = copy(channelPost = Some(message))

  /** New version of a channel post that is known to the bot and was edited */
  def withEditedChannelPost(message: Message): Update = copy(editedChannelPost = Some(message))

  /** New incoming [[https://core.telegram.org/bots/api#inline-mode inline]] query */
  def withInlineQuery(inlineQuery: InlineQuery): Update = copy(inlineQuery = Some(inlineQuery))

  /** The result of an [[https://core.telegram.org/bots/api#inline-mode inline]] query that was chosen by a user and
    * sent to their chat partner. Please see our documentation on the
    * [[https://core.telegram.org/bots/inline#collecting-feedback feedback collecting]] for details on how to enable
    * these updates for your bot.
    */
  def withChosenInlineResult(result: ChosenInlineResult): Update = copy(chosenInlineResult = Some(result))

  /** New incoming callback query */
  def withCallbackQuery(callbackQuery: CallbackQuery): Update = copy(callbackQuery = Some(callbackQuery))

  /** New incoming shipping query. Only for invoices with flexible price */
  def withShippingQuery(shippingQuery: ShippingQuery): Update = copy(shippingQuery = Some(shippingQuery))

  /** New incoming pre-checkout query. Contains full information about checkout */
  def withPreCheckoutQuery(preCheckoutQuery: PreCheckoutQuery): Update = copy(preCheckoutQuery = Some(preCheckoutQuery))

  /** New poll state. Bots receive only updates about stopped polls and polls, which are sent by the bot */
  def withPoll(poll: Poll): Update = copy(poll = Some(poll))

  /** A user changed their answer in a non-anonymous poll. Bots receive new votes only in polls that were sent by the
    * bot itself.
    */
  def withPollAnswer(answer: PollAnswer): Update = copy(pollAnswer = Some(answer))

  /** The bot's chat member status was updated in a chat. For private chats, this update is received only when the bot
    * is blocked or unblocked by the user.
    */
  def withMyChatMember(updated: ChatMemberUpdated): Update = copy(myChatMember = Some(updated))

  /** A chat member's status was updated in a chat. The bot must be an administrator in the chat and must explicitly
    * specify “chat_member” in the list of allowed_updates to receive these updates.
    */
  def withChatMember(updated: ChatMemberUpdated): Update = copy(chatMember = Some(updated))

  /** A request to join the chat has been sent. The bot must have the can_invite_users administrator right in the chat
    * to receive these updates.
    */
  def withChatJoinRequest(request: ChatJoinRequest): Update = copy(chatJoinRequest = Some(request))
}
