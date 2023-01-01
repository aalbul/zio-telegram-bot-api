package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.GetStickerSet.GetStickerSetPayload
import io.github.aalbul.zio.telegram.domain.StickerSet
import io.github.aalbul.zio.telegram.test.BaseSpec

class GetStickerSetSpec extends BaseSpec {
  trait Scope {
    val command: Command[StickerSet] = GetStickerSet.of("my sticker set")
    val payload: GetStickerSetPayload = GetStickerSetPayload(name = "my sticker set")
  }

  "GetStickerSet" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "getStickerSet"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "GetStickerSetPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/get-sticker-set-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[GetStickerSetPayload](
            "json/command/get-sticker-set-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
