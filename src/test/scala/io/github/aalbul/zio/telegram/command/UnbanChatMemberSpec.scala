package io.github.aalbul.zio.telegram.command

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.command.UnbanChatMember.UnbanChatMemberPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class UnbanChatMemberSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] =
      UnbanChatMember
        .of(chatId = "881", userId = 67)
        .withOnlyIfBanned(true)

    val payload: UnbanChatMemberPayload = UnbanChatMemberPayload(
      chatId = "881",
      userId = 67,
      onlyIfBanned = Some(true)
    )
  }

  "UnbanChatMember" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "unbanChatMember"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "UnbanChatMemberPayload" should {
      "serialize payload to json" in new Scope {
        payload.asJson shouldBe jsonResource("json/command/unban-chat-member-payload.json")
      }
    }
  }
}
