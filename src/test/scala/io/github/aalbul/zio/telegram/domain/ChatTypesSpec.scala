package io.github.aalbul.zio.telegram.domain

import io.circe.Json
import io.github.aalbul.zio.telegram.domain.ChatTypes.*
import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatTypesSpec extends BaseSpec {
  "ChatTypes" when {
    "chatTypeDecoder" should {
      "properly decode chat types" in {
        Json.fromString("private").as[ChatType].toTry.get shouldBe ChatTypes.Private
        Json.fromString("supergroup").as[ChatType].toTry.get shouldBe ChatTypes.Supergroup
      }
    }
  }
}
