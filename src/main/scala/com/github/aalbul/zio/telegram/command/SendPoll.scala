package com.github.aalbul.zio.telegram.command

import com.github.aalbul.zio.telegram.command.SendPoll.SendPollPayload
import com.github.aalbul.zio.telegram.domain.ParseModes.ParseMode
import com.github.aalbul.zio.telegram.domain.PollTypes.PollType
import com.github.aalbul.zio.telegram.domain.{Markup, Message, MessageEntity}
import io.circe.generic.extras.ConfiguredJsonCodec

import java.time.Instant

object SendPoll {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class SendPollPayload(
    chatId: String,
    question: String,
    options: Seq[String],
    isAnonymous: Option[Boolean],
    `type`: Option[PollType],
    allowsMultipleAnswers: Option[Boolean],
    correctOptionId: Option[Long],
    explanation: Option[String],
    explanationParseMode: Option[ParseMode],
    explanationEntities: Option[Seq[MessageEntity]],
    openPeriod: Option[Long],
    closeDate: Option[Instant],
    isClosed: Option[Boolean],
    disableNotification: Option[Boolean],
    protectContent: Option[Boolean],
    replyToMessageId: Option[Long],
    allowSendingWithoutReply: Option[Boolean],
    replyMarkup: Option[Markup]
  )

  def of(chatId: String, question: String, options: Seq[String]): SendPoll = SendPoll(
    SendPollPayload(
      chatId = chatId,
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

case class SendPoll(payload: SendPollPayload) extends Command[Message] {
  override val name: String = "sendPoll"

  override def parameters: ApiParameters = JsonBody(payload)

  def withIsAnonymous(anonymous: Boolean): SendPoll = copy(payload = payload.copy(isAnonymous = Some(anonymous)))
  def withType(`type`: PollType): SendPoll = copy(payload = payload.copy(`type` = Some(`type`)))
  def withAllowsMultipleAnswers(allows: Boolean): SendPoll =
    copy(payload = payload.copy(allowsMultipleAnswers = Some(allows)))
  def withCorrectOptionId(id: Long): SendPoll = copy(payload = payload.copy(correctOptionId = Some(id)))
  def withExplanation(explanation: String): SendPoll = copy(payload = payload.copy(explanation = Some(explanation)))
  def withExplanationParseMode(parseMode: ParseMode): SendPoll =
    copy(payload = payload.copy(explanationParseMode = Some(parseMode)))
  def withExplanationEntities(entities: Seq[MessageEntity]): SendPoll =
    copy(payload = payload.copy(explanationEntities = Some(entities)))
  def withOpenPeriod(period: Long): SendPoll = copy(payload = payload.copy(openPeriod = Some(period)))
  def withCloseDate(date: Instant): SendPoll = copy(payload = payload.copy(closeDate = Some(date)))
  def withIsClosed(closed: Boolean): SendPoll = copy(payload = payload.copy(isClosed = Some(closed)))
  def withDisableNotification(disable: Boolean): SendPoll = copy(payload.copy(disableNotification = Some(disable)))
  def withProtectContent(protect: Boolean): SendPoll = copy(payload.copy(protectContent = Some(protect)))
  def withReplyToMessageId(id: Long): SendPoll = copy(payload.copy(replyToMessageId = Some(id)))
  def withAllowSendingWithoutReply(allow: Boolean): SendPoll = copy(
    payload.copy(allowSendingWithoutReply = Some(allow))
  )
  def withReplyMarkup(markup: Markup): SendPoll = copy(payload.copy(replyMarkup = Some(markup)))
}
