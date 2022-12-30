package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.GetGameHighScores.GetGameHighScoresPayload
import io.github.aalbul.zio.telegram.domain.GameHighScore

object GetGameHighScores {
  object GetGameHighScoresPayload {
    implicit val getGameHighScoresPayloadJsonCodec: JsonValueCodec[GetGameHighScoresPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class GetGameHighScoresPayload(
    userId: Long,
    chatId: Option[Long],
    messageId: Option[Long],
    inlineMessageId: Option[String]
  )

  /** Constructs minimal [[GetGameHighScores]] command
    * @param userId
    *   Target user id
    * @return
    *   [[GetGameHighScores]] builder
    */
  def of(userId: Long): GetGameHighScores = GetGameHighScores(
    GetGameHighScoresPayload(
      userId = userId,
      chatId = None,
      messageId = None,
      inlineMessageId = None
    )
  )
}

/** Use this method to get data for high score tables. Will return the score of the specified user and several of their
  * neighbors in a game. Returns an Array of [[https://core.telegram.org/bots/api#gamehighscore GameHighScore]] objects.
  *
  * This method will currently return scores for the target user, plus two of their closest neighbors on each side. Will
  * also return the top three users if the user and their neighbors are not among them. Please note that this behavior
  * is subject to change.
  */
case class GetGameHighScores(payload: GetGameHighScoresPayload) extends Command[Seq[GameHighScore]] {
  override val name: String = "getGameHighScores"
  override def parameters: ApiParameters = JsonBody(payload)

  /** Required if inline_message_id is not specified. Unique identifier for the target chat
    */
  def withChatId(chatId: Long): GetGameHighScores = copy(payload.copy(chatId = Some(chatId)))

  /** Required if inline_message_id is not specified. Identifier of the sent message
    */
  def withMessageId(messageId: Long): GetGameHighScores = copy(payload.copy(messageId = Some(messageId)))

  /** Required if chat_id and message_id are not specified. Identifier of the inline message
    */
  def withInlineMessageId(inlineMessageId: String): GetGameHighScores = copy(
    payload.copy(inlineMessageId = Some(inlineMessageId))
  )
}
