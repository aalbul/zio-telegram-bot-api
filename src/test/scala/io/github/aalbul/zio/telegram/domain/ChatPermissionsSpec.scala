package io.github.aalbul.zio.telegram.domain

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatPermissionsSpec extends BaseSpec {
  "ChatPermissions" when {
    "encoder" should {
      "encode chat permissions to json" in {
        chatPermissions1.asJson shouldBe jsonResource("json/model/chat-permissions.json")
      }
    }

    "decoder" should {
      "decode chat permissions from json" in {
        jsonResourceAs[ChatPermissions]("json/model/chat-permissions.json") shouldBe chatPermissions1
      }
    }
  }
}
