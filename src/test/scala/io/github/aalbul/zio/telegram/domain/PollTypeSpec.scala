package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.{readFromString, writeToString}
import io.github.aalbul.zio.telegram.test.BaseSpec

class PollTypeSpec extends BaseSpec {
  "PollType" when {
    "decoder" should {
      "decode poll types from json" in {
        readFromString[PollType]("\"quiz\"") shouldBe PollType.Quiz
        readFromString[PollType]("\"regular\"") shouldBe PollType.Regular
      }
    }

    "encoder" should {
      "encode poll types to json" in {
        writeToString[PollType](PollType.Quiz) shouldBe "\"quiz\""
        writeToString[PollType](PollType.Regular) shouldBe "\"regular\""
      }
    }
  }
}
