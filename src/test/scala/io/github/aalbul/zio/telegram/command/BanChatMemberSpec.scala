package io.github.aalbul.zio.telegram.command

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.command.BanChatMember.BanChatMemberPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

import java.time.Instant

class BanChatMemberSpec extends BaseSpec {
  trait Scope {
    private val date = Instant.parse("2022-06-10T08:12:51.00Z")

    val command: Command[Boolean] = BanChatMember
      .of(chatId = "881", userId = "misterx")
      .withUntilDate(date)
      .withRevokeMessages(true)

    val payload: BanChatMemberPayload = BanChatMemberPayload(
      chatId = "881",
      userId = "misterx",
      untilDate = Some(date),
      revokeMessages = Some(true)
    )
  }

  "BanChatMember" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "banChatMember"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "BanChatMemberPayload" should {
      "serialize payload to json" in new Scope {
        payload.asJson shouldBe jsonResource("json/command/ban-chat-member-payload.json")
      }
    }
  }
}
