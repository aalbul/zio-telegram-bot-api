package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.PromoteChatMember.PromoteChatMemberPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class PromoteChatMemberSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] =
      PromoteChatMember
        .of(chatId = "445", userId = 12)
        .withAsAnonymous(true)
        .withCanManageChat(false)
        .withCanPostMessages(true)
        .withCanEditMessages(false)
        .withCanDeleteMessages(true)
        .withCanManageVideoChats(false)
        .withCanRestrictMembers(true)
        .withCanPromoteMembers(false)
        .withCanChangeInfo(true)
        .withCanInviteUsers(false)
        .withCanPinMessages(true)
        .withCanManageTopics(true)

    val payload: PromoteChatMemberPayload = PromoteChatMemberPayload(
      chatId = "445",
      userId = 12,
      isAnonymous = Some(true),
      canManageChat = Some(false),
      canPostMessages = Some(true),
      canEditMessages = Some(false),
      canDeleteMessages = Some(true),
      canManageVideoChats = Some(false),
      canRestrictMembers = Some(true),
      canPromoteMembers = Some(false),
      canChangeInfo = Some(true),
      canInviteUsers = Some(false),
      canPinMessages = Some(true),
      canManageTopics = Some(true)
    )
  }

  "PromoteChatMember" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "promoteChatMember"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "PromoteChatMemberPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/promote-chat-member-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[PromoteChatMemberPayload]("json/command/promote-chat-member-payload.json") shouldBe payload
        }
      }
    }
  }
}
