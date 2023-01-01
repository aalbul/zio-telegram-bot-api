package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.SetStickerPositionInSet.SetStickerPositionInSetPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class SetStickerPositionInSetSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = SetStickerPositionInSet.of(sticker = "sticker-1", position = 3)
    val payload: SetStickerPositionInSetPayload = SetStickerPositionInSetPayload(sticker = "sticker-1", position = 3)
  }

  "SetStickerPositionInSet" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "setStickerPositionInSet"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "SetStickerPositionInSetPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/set-sticker-position-in-set-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[SetStickerPositionInSetPayload](
            "json/command/set-sticker-position-in-set-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
