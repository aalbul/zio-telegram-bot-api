package io.github.aalbul.zio.telegram.command

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.command.ExportChatInviteLink.ExportChatInviteLinkPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class ExportChatInviteLinkSpec extends BaseSpec {
  trait Scope {
    val command: Command[String] = ExportChatInviteLink.of("559")

    val payload: ExportChatInviteLinkPayload = ExportChatInviteLinkPayload(chatId = "559")
  }

  "ExportChatInviteLink" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "exportChatInviteLink"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "ExportChatInviteLinkPayload" should {
      "serialize payload to json" in new Scope {
        payload.asJson shouldBe jsonResource("json/command/export-chat-invite-link-payload.json")
      }
    }
  }
}
