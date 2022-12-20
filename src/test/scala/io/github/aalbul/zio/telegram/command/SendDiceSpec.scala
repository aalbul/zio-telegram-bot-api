package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.SendDice.SendDicePayload
import io.github.aalbul.zio.telegram.domain.{DiceType, Message}
import io.github.aalbul.zio.telegram.test.BaseSpec

class SendDiceSpec extends BaseSpec {
  trait Scope {
    val command: Command[Message] =
      SendDice
        .of("123")
        .withMessageThreadId(28)
        .withEmoji(DiceType.SlotMachine)
        .withDisableNotification(false)
        .withProtectContent(true)
        .withReplyToMessageId(811)
        .withAllowSendingWithoutReply(false)
        .withReplyMarkup(forceReplyMarkup1)

    val payload: SendDicePayload = SendDicePayload(
      chatId = "123",
      messageThreadId = Some(28),
      emoji = Some(DiceType.SlotMachine),
      disableNotification = Some(false),
      protectContent = Some(true),
      replyToMessageId = Some(811),
      allowSendingWithoutReply = Some(false),
      replyMarkup = Some(forceReplyMarkup1)
    )
  }

  "SendDice" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "sendDice"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "SendDicePayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/send-dice-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[SendDicePayload]("json/command/send-dice-payload.json") shouldBe payload
        }
      }
    }
  }
}
