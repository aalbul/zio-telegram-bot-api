package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatJoinRequestSpec extends BaseSpec {
  "ChatJoinRequest" when {
    "encoder" should {
      "encode chat join request to json" in {
        writeToString(chatJoinRequest1) should matchJsonResource("json/model/chat-join-request.json")
      }
    }

    "decoder" should {
      "decode chat join request from json" in {
        jsonResourceAs[ChatJoinRequest]("json/model/chat-join-request.json") shouldBe chatJoinRequest1
      }
    }
  }
}
