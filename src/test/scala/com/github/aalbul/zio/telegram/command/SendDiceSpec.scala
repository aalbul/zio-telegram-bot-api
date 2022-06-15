package com.github.aalbul.zio.telegram.command

import com.github.aalbul.zio.telegram.command.SendDice.SendDicePayload
import com.github.aalbul.zio.telegram.domain.{DiceTypes, Message}
import com.github.aalbul.zio.telegram.test.BaseSpec
import io.circe.syntax.EncoderOps

class SendDiceSpec extends BaseSpec {
  trait Scope {
    val command: Command[Message] =
      SendDice
        .of("123")
        .withEmoji(DiceTypes.SlotMachine)
        .withDisableNotification(false)
        .withProtectContent(true)
        .withReplyToMessageId(811)
        .withAllowSendingWithoutReply(false)
        .withReplyMarkup(forceReplyMarkup1)

    val payload: SendDicePayload = SendDicePayload(
      chatId = "123",
      emoji = Some(DiceTypes.SlotMachine),
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
      "serialize payload to json" in new Scope {
        payload.asJson shouldBe jsonResource("json/command/send-dice-payload.json")
      }
    }
  }
}
