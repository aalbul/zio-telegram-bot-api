package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import TextMessage.textMessageProjector

class TextMessageSpec extends BaseSpec {
  "TextMessage" when {
    "testMessageProjector" should {
      "properly project text messages" in {
        textMessageProjector.project(textMessage1) shouldBe Some(textMessageProjection)
      }

      "not match any other messages" in {
        (allMessages - textMessage1).flatMap(textMessageProjector.project) shouldBe empty
      }
    }
  }
}
