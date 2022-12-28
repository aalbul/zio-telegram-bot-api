package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatAdministratorRightsSpec extends BaseSpec {
  "ChatAdministratorRights" when {
    "encoder" should {
      "encode chat administrator rights to json" in {
        writeToString(chatAdministratorRights1) should matchJsonResource("json/model/chat-administrator-rights.json")
      }
    }

    "decoder" should {
      "decode chat administrator rights from json" in {
        jsonResourceAs[ChatAdministratorRights](
          "json/model/chat-administrator-rights.json"
        ) shouldBe chatAdministratorRights1
      }
    }
  }
}
