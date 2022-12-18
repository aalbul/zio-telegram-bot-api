package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.{readFromString, writeToString}
import io.github.aalbul.zio.telegram.test.BaseSpec

class ParseModeSpec extends BaseSpec {
  "ParseMode" when {
    "encoder" should {
      "encode ParseMode to json" in {
        writeToString[ParseMode](ParseMode.MarkdownV2) shouldBe "\"MarkdownV2\""
        writeToString[ParseMode](ParseMode.HTML) shouldBe "\"HTML\""
      }
    }

    "decoder" should {
      "properly decode ParseMode from json" in {
        readFromString[ParseMode]("\"MarkdownV2\"") shouldBe ParseMode.MarkdownV2
        readFromString[ParseMode]("\"HTML\"") shouldBe ParseMode.HTML
      }
    }
  }
}
