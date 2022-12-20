package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.SendPoll.SendPollPayload
import io.github.aalbul.zio.telegram.domain.{Message, ParseMode, PollType}
import io.github.aalbul.zio.telegram.test.BaseSpec
import org.scalatest.time.SpanSugar.convertIntToGrainOfTime

import java.time.Instant

class SendPollSpec extends BaseSpec {
  trait Scope {
    val closeDate: Instant = Instant.parse("2022-05-13T10:13:12.00Z")

    val command: Command[Message] =
      SendPoll
        .of(chatId = "441", question = "Are you sure?", options = Seq("Yes", "No", "Not sure"))
        .withMessageThreadId(33)
        .withIsAnonymous(false)
        .withType(PollType.Quiz)
        .withAllowsMultipleAnswers(true)
        .withCorrectOptionId(1)
        .withExplanation("Some explanation")
        .withExplanationParseMode(ParseMode.MarkdownV2)
        .withExplanationEntities(Seq(messageEntity1))
        .withOpenPeriod(100.seconds)
        .withCloseDate(closeDate)
        .withIsClosed(false)
        .withDisableNotification(false)
        .withProtectContent(true)
        .withReplyToMessageId(811)
        .withAllowSendingWithoutReply(false)
        .withReplyMarkup(forceReplyMarkup1)

    val payload: SendPollPayload = SendPollPayload(
      chatId = "441",
      messageThreadId = Some(33),
      question = "Are you sure?",
      options = Seq("Yes", "No", "Not sure"),
      isAnonymous = Some(false),
      `type` = Some(PollType.Quiz),
      allowsMultipleAnswers = Some(true),
      correctOptionId = Some(1),
      explanation = Some("Some explanation"),
      explanationParseMode = Some(ParseMode.MarkdownV2),
      explanationEntities = Some(Seq(messageEntity1)),
      openPeriod = Some(100.seconds),
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
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/send-poll-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[SendPollPayload]("json/command/send-poll-payload.json") shouldBe payload
        }
      }
    }
  }
}
