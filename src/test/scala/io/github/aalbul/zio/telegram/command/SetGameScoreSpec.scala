package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.SetGameScore.SetGameScorePayload
import io.github.aalbul.zio.telegram.domain.MessageOrInlineMessageUpdateResult
import io.github.aalbul.zio.telegram.test.BaseSpec

class SetGameScoreSpec extends BaseSpec {
  trait Scope {
    val command: Command[MessageOrInlineMessageUpdateResult] = SetGameScore
      .of(userId = 22, score = 121)
      .withForce(true)
      .withDisableEditMessage(false)
      .withChatId(31)
      .withMessageId(99)
      .withInlineMessageId("91")

    val payload: SetGameScorePayload = SetGameScorePayload(
      userId = 22,
      score = 121,
      force = Some(true),
      disableEditMessage = Some(false),
      chatId = Some(31),
      messageId = Some(99),
      inlineMessageId = Some("91")
    )
  }

  "SetGameScore" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "setGameScore"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "SetGameScorePayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/set-game-score-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[SetGameScorePayload]("json/command/set-game-score-payload.json") shouldBe payload
        }
      }
    }
  }
}
