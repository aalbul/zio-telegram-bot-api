package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.DeleteStickerFromSet.DeleteStickerFromSetPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class DeleteStickerFromSetSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = DeleteStickerFromSet.of(sticker = "sticker-1")
    val payload: DeleteStickerFromSetPayload = DeleteStickerFromSetPayload(sticker = "sticker-1")
  }

  "DeleteStickerFromSet" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "deleteStickerFromSet"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "DeleteStickerFromSetPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/delete-sticker-from-set-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[DeleteStickerFromSetPayload](
            "json/command/delete-sticker-from-set-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
