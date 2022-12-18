package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatPermissionsSpec extends BaseSpec {
  "ChatPermissions" when {
    "encoder" should {
      "encode chat permissions to json" in {
        writeToString(chatPermissions1) should matchJsonResource("json/model/chat-permissions.json")
      }
    }

    "decoder" should {
      "decode chat permissions from json" in {
        jsonResourceAs[ChatPermissions]("json/model/chat-permissions.json") shouldBe chatPermissions1
      }
    }
  }
}
