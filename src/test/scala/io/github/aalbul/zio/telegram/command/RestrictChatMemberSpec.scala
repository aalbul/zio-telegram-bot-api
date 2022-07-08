package io.github.aalbul.zio.telegram.command

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.command.RestrictChatMember.RestrictChatMemberPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class RestrictChatMemberSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] =
      RestrictChatMember
        .of(chatId = "441", userId = 31, permissions = chatPermissions1)
        .withUntilDate(instant2)

    val payload: RestrictChatMemberPayload = RestrictChatMemberPayload(
      chatId = "441",
      userId = 31,
      permissions = chatPermissions1,
      untilDate = Some(instant2)
    )
  }

  "RestrictChatMember" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "restrictChatMember"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "RestrictChatMemberPayload" should {
      "serialize payload to json" in new Scope {
        payload.asJson shouldBe jsonResource("json/command/restrict-chat-member-payload.json")
      }
    }
  }
}
