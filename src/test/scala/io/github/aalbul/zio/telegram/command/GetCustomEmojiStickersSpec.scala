package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.GetCustomEmojiStickers.GetCustomEmojiStickersPayload
import io.github.aalbul.zio.telegram.domain.Sticker
import io.github.aalbul.zio.telegram.test.BaseSpec

class GetCustomEmojiStickersSpec extends BaseSpec {
  trait Scope {
    val command: Command[Seq[Sticker]] =
      GetCustomEmojiStickers.of(customEmojiIds = Seq("custom-emoji-1", "custom-emoji-5"))
    val payload: GetCustomEmojiStickersPayload =
      GetCustomEmojiStickersPayload(customEmojiIds = Seq("custom-emoji-1", "custom-emoji-5"))
  }

  "GetCustomEmojiStickers" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "getCustomEmojiStickers"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "GetCustomEmojiStickersPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/get-custom-emoji-stickers-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[GetCustomEmojiStickersPayload](
            "json/command/get-custom-emoji-stickers-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
