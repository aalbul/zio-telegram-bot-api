package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatMemberUpdatedSpec extends BaseSpec {
  "ChatMemberUpdated" when {
    "encoder" should {
      "encode chat member updated to json" in {
        writeToString(chatMemberUpdated1) should matchJsonResource("json/model/chat-member-updated.json")
      }
    }

    "decoder" should {
      "decode chat member updated from json" in {
        jsonResourceAs[ChatMemberUpdated]("json/model/chat-member-updated.json") shouldBe chatMemberUpdated1
      }
    }
  }
}
