package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import java.time.Instant
import scala.concurrent.duration.Duration

object Poll {
  implicit val pollJsonCodec: JsonValueCodec[Poll] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[Poll]]
    * @param id
    *   Unique poll identifier
    * @param question
    *   Poll question, 1-300 characters
    * @param options
    *   List of poll options
    * @param totalVoterCount
    *   Total number of users that voted in the poll
    * @param isClosed
    *   True, if the poll is closed
    * @param isAnonymous
    *   True, if the poll is anonymous
    * @param `type`
    *   Poll type, currently can be “regular” or “quiz”
    * @param allowsMultipleAnswers
    *   True, if the poll allows multiple answers
    * @return
    *   [[Poll]] builder
    */
  def of(
    id: String,
    question: String,
    options: Seq[PollOption],
    totalVoterCount: Long,
    isClosed: Boolean,
    isAnonymous: Boolean,
    `type`: PollType,
    allowsMultipleAnswers: Boolean
  ): Poll = Poll(
    id = id,
    question = question,
    options = options,
    totalVoterCount = totalVoterCount,
    isClosed = isClosed,
    isAnonymous = isAnonymous,
    `type` = `type`,
    allowsMultipleAnswers = allowsMultipleAnswers,
    correctOptionId = None,
    explanation = None,
    explanationEntities = None,
    openPeriod = None,
    closeDate = None
  )
}

/** This object contains information about a poll.
  */
case class Poll(
  id: String,
  question: String,
  options: Seq[PollOption],
  totalVoterCount: Long,
  isClosed: Boolean,
  isAnonymous: Boolean,
  `type`: PollType,
  allowsMultipleAnswers: Boolean,
  correctOptionId: Option[Long],
  explanation: Option[String],
  explanationEntities: Option[Seq[MessageEntity]],
  openPeriod: Option[Duration],
  closeDate: Option[Instant]
) {

  /** 0-based identifier of the correct answer option. Available only for polls in the quiz mode, which are closed, or
    * was sent (not forwarded) by the bot or to the private chat with the bot.
    */
  def withCorrectOptionId(correctOptionId: Long): Poll = copy(correctOptionId = Some(correctOptionId))

  /** Text that is shown when a user chooses an incorrect answer or taps on the lamp icon in a quiz-style poll, 0-200
    * characters
    */
  def withExplanation(explanation: String): Poll = copy(explanation = Some(explanation))

  /** Special entities like usernames, URLs, bot commands, etc. that appear in the explanation
    */
  def withExplanationEntities(explanationEntities: Seq[MessageEntity]): Poll =
    copy(explanationEntities = Some(explanationEntities))

  /** Amount of time in seconds the poll will be active after creation
    */
  def withOpenPeriod(openPeriod: Duration): Poll = copy(openPeriod = Some(openPeriod))

  /** Point in time (Unix timestamp) when the poll will be automatically closed
    */
  def withCloseDate(closeDate: Instant): Poll = copy(closeDate = Some(closeDate))
}
