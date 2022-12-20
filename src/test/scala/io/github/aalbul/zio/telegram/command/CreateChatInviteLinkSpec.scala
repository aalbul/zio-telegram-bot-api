package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.CreateChatInviteLink.CreateChatInviteLinkPayload
import io.github.aalbul.zio.telegram.domain.ChatInviteLink
import io.github.aalbul.zio.telegram.test.BaseSpec

class CreateChatInviteLinkSpec extends BaseSpec {
  trait Scope {
    val command: Command[ChatInviteLink] =
      CreateChatInviteLink
        .of("348")
        .withName("link one")
        .withExpireDate(instant3)
        .withMemberLimit(20)
        .withCreatesJoinRequest(true)

    val payload: CreateChatInviteLinkPayload = CreateChatInviteLinkPayload(
      chatId = "348",
      name = Some("link one"),
      expireDate = Some(instant3),
      memberLimit = Some(20),
      createsJoinRequest = Some(true)
    )
  }

  "CreateChatInviteLink" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "createChatInviteLink"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "CreateChatInviteLinkPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/create-chat-invite-link-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[CreateChatInviteLinkPayload](
            "json/command/create-chat-invite-link-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
