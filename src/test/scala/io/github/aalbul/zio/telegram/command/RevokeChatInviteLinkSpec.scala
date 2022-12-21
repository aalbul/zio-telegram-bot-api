package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.RevokeChatInviteLink.RevokeChatInviteLinkPayload
import io.github.aalbul.zio.telegram.domain.ChatInviteLink
import io.github.aalbul.zio.telegram.test.BaseSpec

class RevokeChatInviteLinkSpec extends BaseSpec {
  trait Scope {
    val command: Command[ChatInviteLink] = RevokeChatInviteLink
      .of(chatId = "351", inviteLink = "https://invite.link/one")

    val payload = RevokeChatInviteLinkPayload(
      chatId = "351",
      inviteLink = "https://invite.link/one"
    )
  }

  "RevokeChatInviteLink" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "revokeChatInviteLink"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "RevokeChatInviteLinkPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/revoke-chat-invite-link-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[RevokeChatInviteLinkPayload](
            "json/command/revoke-chat-invite-link-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
