package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.GetGameHighScores.GetGameHighScoresPayload
import io.github.aalbul.zio.telegram.domain.GameHighScore
import io.github.aalbul.zio.telegram.test.BaseSpec

class GetGameHighScoresSpec extends BaseSpec {
  trait Scope {
    val command: Command[Seq[GameHighScore]] = GetGameHighScores
      .of(userId = 22)
      .withChatId(12)
      .withMessageId(192)
      .withInlineMessageId("120")

    val payload: GetGameHighScoresPayload = GetGameHighScoresPayload(
      userId = 22,
      chatId = Some(12),
      messageId = Some(192),
      inlineMessageId = Some("120")
    )
  }

  "GetGameHighScores" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "getGameHighScores"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "GetGameHighScoresPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/get-game-high-scores-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[GetGameHighScoresPayload]("json/command/get-game-high-scores-payload.json") shouldBe payload
        }
      }
    }
  }
}
