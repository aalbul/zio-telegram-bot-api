package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.SendContact.SendContactPayload
import io.github.aalbul.zio.telegram.domain.Message
import io.github.aalbul.zio.telegram.test.BaseSpec

class SendContactSpec extends BaseSpec {
  trait Scope {
    val vcard: String =
      """|begin:VCARD
         |source:ldap://cn=bjorn%20Jensen, o=university%20of%20Michigan, c=US
         |name:Bjorn Jensen
         |fn:Bj=F8rn Jensen
         |n:Jensen;Bj=F8rn
         |email;type=internet:bjorn@umich.edu
         |tel;type=work,voice,msg:+1 313 747-4454
         |key;type=x509;encoding=B:dGhpcyBjb3VsZCBiZSAKbXkgY2VydGlmaWNhdGUK
         |end:VCARD""".stripMargin

    val command: Command[Message] =
      SendContact
        .of(chatId = "442", phoneNumber = "+31631234567", firstName = "Bjorn")
        .withLastName("Jensen")
        .withVcard(vcard)
        .withDisableNotification(false)
        .withProtectContent(true)
        .withReplyToMessageId(811)
        .withAllowSendingWithoutReply(false)
        .withReplyMarkup(forceReplyMarkup1)

    val payload: SendContactPayload = SendContactPayload(
      chatId = "442",
      phoneNumber = "+31631234567",
      firstName = "Bjorn",
      lastName = Some("Jensen"),
      vcard = Some(vcard),
      disableNotification = Some(false),
      protectContent = Some(true),
      replyToMessageId = Some(811),
      allowSendingWithoutReply = Some(false),
      replyMarkup = Some(forceReplyMarkup1)
    )
  }

  "SendContact" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "sendContact"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "SendContactPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/send-contact-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[SendContactPayload]("json/command/send-contact-payload.json") shouldBe payload
        }
      }
    }
  }
}
