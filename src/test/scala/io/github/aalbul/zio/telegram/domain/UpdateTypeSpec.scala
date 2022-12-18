package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.{readFromString, writeToString}
import io.github.aalbul.zio.telegram.test.BaseSpec

class UpdateTypeSpec extends BaseSpec {
  "UpdateTypes" when {
    "encoder" should {
      "encode update type to json" in {
        writeToString[UpdateType](UpdateType.ChatJoinRequest) shouldBe "\"chat_join_request\""
        writeToString[UpdateType](UpdateType.CallbackQuery) shouldBe "\"callback_query\""
      }
    }

    "decoder" should {
      "decode update type from json" in {
        readFromString[UpdateType]("\"chat_join_request\"") shouldBe UpdateType.ChatJoinRequest
        readFromString[UpdateType]("\"callback_query\"") shouldBe UpdateType.CallbackQuery
      }
    }
  }
}
