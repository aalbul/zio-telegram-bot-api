package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.SendPoll.SendPollPayload
import io.github.aalbul.zio.telegram.domain.*
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import java.time.Instant
import scala.concurrent.duration.Duration

object SendPoll {
  object SendPollPayload {
    implicit val sendPollPayloadJsonCodec: JsonValueCodec[SendPollPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class SendPollPayload(
    chatId: String,
    messageThreadId: Option[Long],
    question: String,
    options: Seq[String],
    isAnonymous: Option[Boolean],
    `type`: Option[PollType],
    allowsMultipleAnswers: Option[Boolean],
    correctOptionId: Option[Long],
    explanation: Option[String],
    explanationParseMode: Option[ParseMode],
    explanationEntities: Option[Seq[MessageEntity]],
    openPeriod: Option[Duration],
    closeDate: Option[Instant],
    isClosed: Option[Boolean],
    disableNotification: Option[Boolean],
    protectContent: Option[Boolean],
    replyToMessageId: Option[Long],
    allowSendingWithoutReply: Option[Boolean],
    replyMarkup: Option[Markup]
  )

  /** Constructs minimal [[SendPoll]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param question
    *   Poll question, 1-300 characters
    * @param options
    *   A JSON-serialized list of answer options, 2-10 strings 1-100 characters each
    * @return
    *   [[SendPoll]] builder
    */
  def of(chatId: String, question: String, options: Seq[String]): SendPoll = SendPoll(
    SendPollPayload(
      chatId = chatId,
      messageThreadId = None,
      question = question,
      options = options,
      isAnonymous = None,
      `type` = None,
      allowsMultipleAnswers = None,
      correctOptionId = None,
      explanation = None,
      explanationParseMode = None,
      explanationEntities = None,
      openPeriod = None,
      closeDate = None,
      isClosed = None,
      disableNotification = None,
      protectContent = None,
      replyToMessageId = None,
      allowSendingWithoutReply = None,
      replyMarkup = None
    )
  )
}

/** Use this method to send a native poll. On success, the sent [[Message]] is returned.
  */
case class SendPoll(payload: SendPollPayload) extends Command[Message] {
  override val name: String = "sendPoll"

  override def parameters: ApiParameters = JsonBody(payload)

  def withMessageThreadId(messageThreadId: Long): SendPoll = copy(payload.copy(messageThreadId = Some(messageThreadId)))

  /** ''True'', if the poll needs to be anonymous, defaults to True
    */
  def withIsAnonymous(anonymous: Boolean): SendPoll = copy(payload.copy(isAnonymous = Some(anonymous)))

  /** Poll type, “quiz” or “regular”, defaults to “regular”
    */
  def withType(`type`: PollType): SendPoll = copy(payload.copy(`type` = Some(`type`)))

  /** ''True'', if the poll allows multiple answers, ignored for polls in quiz mode, defaults to False
    */
  def withAllowsMultipleAnswers(allows: Boolean): SendPoll = copy(payload.copy(allowsMultipleAnswers = Some(allows)))

  /** 0-based identifier of the correct answer option, required for polls in quiz mode
    */
  def withCorrectOptionId(id: Long): SendPoll = copy(payload.copy(correctOptionId = Some(id)))

  /** Text that is shown when a user chooses an incorrect answer or taps on the lamp icon in a quiz-style poll, 0-200
    * characters with at most 2 line feeds after entities parsing
    */
  def withExplanation(explanation: String): SendPoll = copy(payload.copy(explanation = Some(explanation)))

  /** Mode for parsing entities in the explanation. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withExplanationParseMode(parseMode: ParseMode): SendPoll = copy(
    payload.copy(explanationParseMode = Some(parseMode))
  )

  /** A JSON-serialized list of special entities that appear in the poll explanation, which can be specified instead of
    * ''parse_mode''
    */
  def withExplanationEntities(entities: Seq[MessageEntity]): SendPoll = copy(
    payload.copy(explanationEntities = Some(entities))
  )

  /** Amount of time in seconds the poll will be active after creation, 5-600. Can't be used together with
    * ''close_date''.
    */
  def withOpenPeriod(period: Duration): SendPoll = copy(payload.copy(openPeriod = Some(period)))

  /** Point in time (Unix timestamp) when the poll will be automatically closed. Must be at least 5 and no more than 600
    * seconds in the future. Can't be used together with ''open_period''.
    */
  def withCloseDate(date: Instant): SendPoll = copy(payload.copy(closeDate = Some(date)))

  /** Pass ''True'', if the poll needs to be immediately closed. This can be useful for poll preview.
    */
  def withIsClosed(closed: Boolean): SendPoll = copy(payload.copy(isClosed = Some(closed)))

  /** Sends the message [[https://telegram.org/blog/channels-2-0#silent-messages silently]]. Users will receive a
    * notification with no sound.
    */
  def withDisableNotification(disable: Boolean): SendPoll = copy(payload.copy(disableNotification = Some(disable)))

  /** Protects the contents of the sent message from forwarding and saving
    */
  def withProtectContent(protect: Boolean): SendPoll = copy(payload.copy(protectContent = Some(protect)))

  /** If the message is a reply, ID of the original message
    */
  def withReplyToMessageId(id: Long): SendPoll = copy(payload.copy(replyToMessageId = Some(id)))

  /** Pass ''True'', if the message should be sent even if the specified replied-to message is not found
    */
  def withAllowSendingWithoutReply(allow: Boolean): SendPoll = copy(
    payload.copy(allowSendingWithoutReply = Some(allow))
  )

  /** Additional interface options. An object for an
    * [[https://core.telegram.org/bots#inline-keyboards-and-on-the-fly-updating inline keyboard]],
    * [[https://core.telegram.org/bots#keyboards custom reply keyboard]], instructions to remove reply keyboard or to
    * force a reply from the user.
    */
  def withReplyMarkup(markup: Markup): SendPoll = copy(payload.copy(replyMarkup = Some(markup)))
}
