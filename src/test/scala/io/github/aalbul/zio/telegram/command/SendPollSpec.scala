package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.SendPoll.SendPollPayload
import io.github.aalbul.zio.telegram.domain.{Message, ParseModes, PollTypes}
import io.github.aalbul.zio.telegram.test.BaseSpec
import io.circe.syntax.EncoderOps

import java.time.Instant

class SendPollSpec extends BaseSpec {
  trait Scope {
    val closeDate: Instant = Instant.parse("2022-05-13T10:13:12.00Z")

    val command: Command[Message] =
      SendPoll
        .of(chatId = "441", question = "Are you sure?", options = Seq("Yes", "No", "Not sure"))
        .withIsAnonymous(false)
        .withType(PollTypes.Quiz)
        .withAllowsMultipleAnswers(true)
        .withCorrectOptionId(1)
        .withExplanation("Some explanation")
        .withExplanationParseMode(ParseModes.MarkdownV2)
        .withExplanationEntities(Seq(messageEntity1))
        .withOpenPeriod(100)
        .withCloseDate(closeDate)
        .withIsClosed(false)
        .withDisableNotification(false)
        .withProtectContent(true)
        .withReplyToMessageId(811)
        .withAllowSendingWithoutReply(false)
        .withReplyMarkup(forceReplyMarkup1)

    val payload: SendPollPayload = SendPollPayload(
      chatId = "441",
      question = "Are you sure?",
      options = Seq("Yes", "No", "Not sure"),
      isAnonymous = Some(false),
      `type` = Some(PollTypes.Quiz),
      allowsMultipleAnswers = Some(true),
      correctOptionId = Some(1),
      explanation = Some("Some explanation"),
      explanationParseMode = Some(ParseModes.MarkdownV2),
      explanationEntities = Some(Seq(messageEntity1)),
      openPeriod = Some(100),
      closeDate = Some(closeDate),
      isClosed = Some(false),
      disableNotification = Some(false),
      protectContent = Some(true),
      replyToMessageId = Some(811),
      allowSendingWithoutReply = Some(false),
      replyMarkup = Some(forceReplyMarkup1)
    )
  }

  "SendPoll" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "sendPoll"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "SendPollPayload" should {
      "serialize payload to json" in new Scope {
        payload.asJson shouldBe jsonResource("json/command/send-poll-payload.json")
      }
    }
  }
}
