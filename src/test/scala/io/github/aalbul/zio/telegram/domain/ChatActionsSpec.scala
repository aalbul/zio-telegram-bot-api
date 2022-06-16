package io.github.aalbul.zio.telegram.domain

import io.circe.Json
import io.github.aalbul.zio.telegram.domain.ChatActions.chatActionEncoder
import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatActionsSpec extends BaseSpec {
  "ChatActions" when {
    "chatActionEncoder" should {
      "properly encode actions" in {
        chatActionEncoder(ChatActions.Typing) shouldBe Json.fromString("typing")
        chatActionEncoder(ChatActions.UploadVideo) shouldBe Json.fromString("upload_video")
      }
    }
  }
}
