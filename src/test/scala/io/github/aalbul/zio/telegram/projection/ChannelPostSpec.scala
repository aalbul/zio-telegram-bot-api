package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.projection.ChannelPost.channelPostProjector
import io.github.aalbul.zio.telegram.test.BaseSpec

class ChannelPostSpec extends BaseSpec {
  "ChannelPost" when {
    "channelPostProjector" should {
      "properly project channel messages" in {
        channelPostProjector.project(channelPostAudioMessage) shouldBe Some(channelPostMessageProjection)
      }

      "not match any other updates" in {
        (allUpdates - channelPostAudioMessage).flatMap(channelPostProjector.project) shouldBe empty
      }
    }
  }
}
