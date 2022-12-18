package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.{readFromString, writeToString}
import io.github.aalbul.zio.telegram.domain.ChatType.*
import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatTypeSpec extends BaseSpec {
  "ChatTypes" when {
    "encoder" should {
      "properly encode chat actions" in {
        writeToString[ChatType](ChatType.Private) shouldBe "\"private\""
        writeToString[ChatType](ChatType.Supergroup) shouldBe "\"supergroup\""
      }
    }

    "decoder" should {
      "properly decode chat types" in {
        readFromString[ChatType]("\"private\"") shouldBe ChatType.Private
        readFromString[ChatType]("\"supergroup\"") shouldBe ChatType.Supergroup
      }
    }
  }
}
