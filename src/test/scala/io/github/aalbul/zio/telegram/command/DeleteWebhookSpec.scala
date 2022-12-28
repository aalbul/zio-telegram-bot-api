package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.DeleteWebhook.DeleteWebhookPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class DeleteWebhookSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = DeleteWebhook.of
      .withDropPendingUpdates(true)
    val payload: DeleteWebhookPayload = DeleteWebhookPayload(dropPendingUpdates = Some(true))
  }

  "DeleteWebhook" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "deleteWebhook"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "DeleteWebhookPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/delete-webhook-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[DeleteWebhookPayload]("json/command/delete-webhook-payload.json") shouldBe payload
        }
      }
    }
  }
}
