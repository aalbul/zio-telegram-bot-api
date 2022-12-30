package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.SendGame.SendGamePayload
import io.github.aalbul.zio.telegram.domain.{InlineKeyboardMarkup, Message}

object SendGame {
  object SendGamePayload {
    implicit val sendGamePayloadJsonCodec: JsonValueCodec[SendGamePayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class SendGamePayload(
    chatId: Long,
    messageThreadId: Option[Long],
    gameShortName: String,
    disableNotification: Option[Boolean],
    protectContent: Option[Boolean],
    replyToMessageId: Option[Long],
    allowSendingWithoutReply: Option[Boolean],
    replyMarkup: Option[InlineKeyboardMarkup]
  )

  /** Constructs minimal [[SendGame]] command
    * @param chatId
    *   Unique identifier for the target chat
    * @param gameShortName
    *   Short name of the game, serves as the unique identifier for the game. Set up your games via
    *   [[https://t.me/botfather @BotFather]].
    * @return
    *   [[SendGame]] builder
    */
  def of(chatId: Long, gameShortName: String): SendGame = SendGame(
    SendGamePayload(
      chatId = chatId,
      messageThreadId = None,
      gameShortName = gameShortName,
      disableNotification = None,
      protectContent = None,
      replyToMessageId = None,
      allowSendingWithoutReply = None,
      replyMarkup = None
    )
  )
}

/** Use this method to send a game. On success, the sent [[https://core.telegram.org/bots/api#message Message]] is
  * returned.
  */
case class SendGame(payload: SendGamePayload) extends Command[Message] {
  override val name: String = "sendGame"
  override def parameters: ApiParameters = JsonBody(payload)

  /** Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
    */
  def withMessageThreadId(messageThreadId: Long): SendGame = copy(payload.copy(messageThreadId = Some(messageThreadId)))

  /** Sends the message [[https://telegram.org/blog/channels-2-0#silent-messages silently]]. Users will receive a
    * notification with no sound.
    */
  def withDisableNotification(disableNotification: Boolean): SendGame = copy(
    payload.copy(disableNotification = Some(disableNotification))
  )

  /** Protects the contents of the sent message from forwarding and saving
    */
  def withProtectContent(protectContent: Boolean): SendGame = copy(payload.copy(protectContent = Some(protectContent)))

  /** If the message is a reply, ID of the original message
    */
  def withReplyToMessageId(replyToMessageId: Long): SendGame = copy(
    payload.copy(replyToMessageId = Some(replyToMessageId))
  )

  /** Pass True if the message should be sent even if the specified replied-to message is not found
    */
  def withAllowSendingWithoutReply(allowSendingWithoutReply: Boolean): SendGame = copy(
    payload.copy(allowSendingWithoutReply = Some(allowSendingWithoutReply))
  )

  /** A JSON-serialized object for an [[https://core.telegram.org/bots/features#inline-keyboards inline keyboard]]. If
    * empty, one 'Play game_title' button will be shown. If not empty, the first button must launch the game.
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): SendGame = copy(payload.copy(replyMarkup = Some(replyMarkup)))
}
