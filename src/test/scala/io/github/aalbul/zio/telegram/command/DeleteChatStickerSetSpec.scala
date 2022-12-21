package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.DeleteChatStickerSet.DeleteChatStickerSetPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class DeleteChatStickerSetSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = DeleteChatStickerSet.of(chatId = "701")
    val payload: DeleteChatStickerSetPayload = DeleteChatStickerSetPayload(chatId = "701")
  }

  "DeleteChatStickerSet" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "deleteChatStickerSet"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "DeleteChatStickerSetPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/delete-chat-sticker-set-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[DeleteChatStickerSetPayload](
            "json/command/delete-chat-sticker-set-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
