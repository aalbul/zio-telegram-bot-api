package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.SendDice.SendDicePayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.{DiceType, Markup, Message}

object SendDice {
  object SendDicePayload {
    implicit val sendDicePayloadJsonCodec: JsonValueCodec[SendDicePayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class SendDicePayload(
    chatId: String,
    messageThreadId: Option[Long],
    emoji: Option[DiceType],
    disableNotification: Option[Boolean],
    protectContent: Option[Boolean],
    replyToMessageId: Option[Long],
    allowSendingWithoutReply: Option[Boolean],
    replyMarkup: Option[Markup]
  )

  /** Constructs minimal [[SendDice]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @return
    *   [[SendDice]] builder
    */
  def of(chatId: String): SendDice = SendDice(
    SendDicePayload(
      chatId = chatId,
      messageThreadId = None,
      emoji = None,
      disableNotification = None,
      protectContent = None,
      replyToMessageId = None,
      allowSendingWithoutReply = None,
      replyMarkup = None
    )
  )
}

/** Use this method to send an animated emoji that will display a random value. On success, the sent [[Message]] is
  * returned.
  */
case class SendDice(payload: SendDicePayload) extends Command[Message] {
  override val name: String = "sendDice"

  override def parameters: ApiParameters = JsonBody(payload)

  /** Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
    */
  def withMessageThreadId(messageThreadId: Long): SendDice = copy(payload.copy(messageThreadId = Some(messageThreadId)))

  /** Emoji on which the dice throw animation is based. Currently, must be one of “🎲”, “🎯”, “🏀”, “⚽”, “🎳”, or “🎰”.
    * Dice can have values 1-6 for “🎲”, “🎯” and “🎳”, values 1-5 for “🏀” and “⚽”, and values 1-64 for “🎰”. Defaults
    * to “🎲”
    */
  def withEmoji(emoji: DiceType): SendDice = copy(payload.copy(emoji = Some(emoji)))

  /** Sends the message [[https://telegram.org/blog/channels-2-0#silent-messages silently]]. Users will receive a
    * notification with no sound.
    */
  def withDisableNotification(disable: Boolean): SendDice = copy(payload.copy(disableNotification = Some(disable)))

  /** Protects the contents of the sent message from forwarding and saving
    */
  def withProtectContent(protect: Boolean): SendDice = copy(payload.copy(protectContent = Some(protect)))

  /** If the message is a reply, ID of the original message
    */
  def withReplyToMessageId(id: Long): SendDice = copy(payload.copy(replyToMessageId = Some(id)))

  /** Pass ''True'', if the message should be sent even if the specified replied-to message is not found
    */
  def withAllowSendingWithoutReply(allow: Boolean): SendDice = copy(
    payload.copy(allowSendingWithoutReply = Some(allow))
  )

  /** Additional interface options. An object for an
    * [[https://core.telegram.org/bots#inline-keyboards-and-on-the-fly-updating inline keyboard]],
    * [[https://core.telegram.org/bots#keyboards custom reply keyboard]], instructions to remove reply keyboard or to
    * force a reply from the user.
    */
  def withReplyMarkup(markup: Markup): SendDice = copy(payload.copy(replyMarkup = Some(markup)))
}
