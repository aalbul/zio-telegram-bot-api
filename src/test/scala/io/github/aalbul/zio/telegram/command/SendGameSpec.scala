package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.SendGame.SendGamePayload
import io.github.aalbul.zio.telegram.domain.Message
import io.github.aalbul.zio.telegram.test.BaseSpec

class SendGameSpec extends BaseSpec {
  trait Scope {
    val command: Command[Message] =
      SendGame
        .of(chatId = 221, gameShortName = "my game")
        .withMessageThreadId(28)
        .withDisableNotification(false)
        .withProtectContent(true)
        .withReplyToMessageId(812)
        .withAllowSendingWithoutReply(false)
        .withReplyMarkup(inlineKeyboardMarkup1)

    val payload: SendGamePayload = SendGamePayload(
      chatId = 221,
      messageThreadId = Some(28),
      gameShortName = "my game",
      disableNotification = Some(false),
      protectContent = Some(true),
      replyToMessageId = Some(812),
      allowSendingWithoutReply = Some(false),
      replyMarkup = Some(inlineKeyboardMarkup1)
    )
  }

  "SendGame" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "sendGame"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "SendGamePayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/send-game-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[SendGamePayload]("json/command/send-game-payload.json") shouldBe payload
        }
      }
    }
  }
}
