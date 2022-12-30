package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.SetGameScore.SetGameScorePayload
import io.github.aalbul.zio.telegram.domain.MessageOrInlineMessageUpdateResult

object SetGameScore {
  object SetGameScorePayload {
    implicit val setGameScorePayloadJsonCodec: JsonValueCodec[SetGameScorePayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class SetGameScorePayload(
    userId: Long,
    score: Long,
    force: Option[Boolean],
    disableEditMessage: Option[Boolean],
    chatId: Option[Long],
    messageId: Option[Long],
    inlineMessageId: Option[String]
  )

  /** Constructs minimal [[SetGameScore]] command
    * @param userId
    *   User identifier
    * @param score
    *   New score, must be non-negative
    * @return
    *   [[SetGameScore]] builder
    */
  def of(userId: Long, score: Long): SetGameScore = SetGameScore(
    SetGameScorePayload(
      userId = userId,
      score = score,
      force = None,
      disableEditMessage = None,
      chatId = None,
      messageId = None,
      inlineMessageId = None
    )
  )
}

/** Use this method to set the score of the specified user in a game message. On success, if the message is not an
  * inline message, the [[https://core.telegram.org/bots/api#message Message]] is returned, otherwise True is returned.
  * Returns an error, if the new score is not greater than the user's current score in the chat and force is False.
  */
case class SetGameScore(payload: SetGameScorePayload) extends Command[MessageOrInlineMessageUpdateResult] {
  override val name: String = "setGameScore"
  override def parameters: ApiParameters = JsonBody(payload)

  /** Pass True if the high score is allowed to decrease. This can be useful when fixing mistakes or banning cheaters
    */
  def withForce(force: Boolean): SetGameScore = copy(payload.copy(force = Some(force)))

  /** Pass True if the game message should not be automatically edited to include the current scoreboard
    */
  def withDisableEditMessage(disableEditMessage: Boolean): SetGameScore = copy(
    payload.copy(disableEditMessage = Some(disableEditMessage))
  )

  /** Required if inline_message_id is not specified. Unique identifier for the target chat
    */
  def withChatId(chatId: Long): SetGameScore = copy(payload.copy(chatId = Some(chatId)))

  /** Required if inline_message_id is not specified. Identifier of the sent message
    */
  def withMessageId(messageId: Long): SetGameScore = copy(payload.copy(messageId = Some(messageId)))

  /** Required if chat_id and message_id are not specified. Identifier of the inline message
    */
  def withInlineMessageId(inlineMessageId: String): SetGameScore = copy(
    payload.copy(inlineMessageId = Some(inlineMessageId))
  )
}
