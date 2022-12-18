package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.{readFromString, writeToString}
import io.github.aalbul.zio.telegram.domain.ChatAction.*
import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatActionSpec extends BaseSpec {
  "ChatAction" when {
    "encoder" should {
      "properly encode chat actions" in {
        writeToString[ChatAction](ChatAction.Typing) shouldBe "\"typing\""
        writeToString[ChatAction](ChatAction.UploadVideo) shouldBe "\"upload_video\""
      }
    }

    "decoder" should {
      "properly decode chat actions" in {
        readFromString[ChatAction]("\"typing\"") shouldBe ChatAction.Typing
        readFromString[ChatAction]("\"upload_video\"") shouldBe ChatAction.UploadVideo
      }
    }
  }
}
