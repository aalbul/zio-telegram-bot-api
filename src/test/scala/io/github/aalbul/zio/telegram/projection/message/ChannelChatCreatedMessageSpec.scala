package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import ChannelChatCreatedMessage.channelChatCreatedProjector

class ChannelChatCreatedMessageSpec extends BaseSpec {
  "ChannelChatCreatedMessage" when {
    "channelChatCreatedProjector" should {
      "properly project channel chat created messages" in {
        channelChatCreatedProjector.project(channelChatCreatedMessage1) shouldBe Some(channelChatCreatedProjection)
      }

      "not match any other messages" in {
        (allMessages - channelChatCreatedMessage1).flatMap(channelChatCreatedProjector.project) shouldBe empty
      }
    }
  }
}
