package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatPhotoSpec extends BaseSpec {
  "ChatPhoto" when {
    "encode" should {
      "should encode chat photo to json" in {
        writeToString(chatPhoto1) should matchJsonResource("json/model/chat-photo.json")
      }
    }

    "decoder" should {
      "should decode chat photo from json" in {
        jsonResourceAs[ChatPhoto]("json/model/chat-photo.json") shouldBe chatPhoto1
      }
    }
  }
}
