package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.EditChatInviteLink.EditChatInviteLinkPayload
import io.github.aalbul.zio.telegram.domain.ChatInviteLink
import io.github.aalbul.zio.telegram.test.BaseSpec

class EditChatInviteLinkSpec extends BaseSpec {
  trait Scope {
    val command: Command[ChatInviteLink] =
      EditChatInviteLink
        .of("348", "https://invite.link/one")
        .withName("link one 1")
        .withExpireDate(instant3)
        .withMemberLimit(21)
        .withCreatesJoinRequest(false)

    val payload: EditChatInviteLinkPayload = EditChatInviteLinkPayload(
      chatId = "348",
      inviteLink = "https://invite.link/one",
      name = Some("link one 1"),
      expireDate = Some(instant3),
      memberLimit = Some(21),
      createsJoinRequest = Some(false)
    )
  }

  "EditChatInviteLink" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "editChatInviteLink"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "EditChatInviteLinkPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/edit-chat-invite-link-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[EditChatInviteLinkPayload](
            "json/command/edit-chat-invite-link-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
