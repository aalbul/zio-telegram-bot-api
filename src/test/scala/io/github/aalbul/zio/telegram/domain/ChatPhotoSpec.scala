package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatPhotoSpec extends BaseSpec {
  "ChatPhoto" when {
    "decoder" should {
      "should decode chat photo json" in {
        jsonResourceAs[ChatPhoto]("json/model/chat-photo.json") shouldBe chatPhoto1
      }
    }
  }
}
