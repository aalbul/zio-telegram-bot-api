package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.DeleteChatPhoto.DeleteChatPhotoPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class DeleteChatPhotoSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = DeleteChatPhoto.of(chatId = "9931")
    val payload: DeleteChatPhotoPayload = DeleteChatPhotoPayload(chatId = "9931")
  }

  "DeleteChatPhoto" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "deleteChatPhoto"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "DeleteChatPhotoPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/delete-chat-photo-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[DeleteChatPhotoPayload]("json/command/delete-chat-photo-payload.json") shouldBe payload
        }
      }
    }
  }
}
