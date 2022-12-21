package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.SetChatStickerSet.SetChatStickerSetPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class SetChatStickerSetSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = SetChatStickerSet.of(chatId = "611", stickerSetName = "sticker set one")
    val payload: SetChatStickerSetPayload = SetChatStickerSetPayload(chatId = "611", stickerSetName = "sticker set one")
  }

  "SetChatStickerSet" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "setChatStickerSet"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "SetChatStickerSetPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/set-chat-sticker-set-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[SetChatStickerSetPayload]("json/command/set-chat-sticker-set-payload.json") shouldBe payload
        }
      }
    }
  }
}
