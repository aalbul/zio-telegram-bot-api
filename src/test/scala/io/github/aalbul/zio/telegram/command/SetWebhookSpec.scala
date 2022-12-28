package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.FileDescriptor.pathDescriptor
import io.github.aalbul.zio.telegram.command.MultipartBody.{filePart, stringPart}
import io.github.aalbul.zio.telegram.domain.UpdateType
import io.github.aalbul.zio.telegram.test.BaseSpec

class SetWebhookSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] =
      SetWebhook
        .of(url = "https://google.com/webhook")
        .withCertificate(pathDescriptor("/tmp/cert.pem"))
        .withIpAddress("192.168.2.1")
        .withMaxConnections(25)
        .withAllowedUpdates(Set(UpdateType.Message, UpdateType.Poll))
        .withDropPendingUpdates(true)
        .withSecretToken("token-1")
  }

  "SetWebhook" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "setWebhook"
      }
    }

    "parameters" should {
      "represent parameters as form data" in new Scope {
        command.parameters shouldBe MultipartBody.of(
          stringPart("url", "https://google.com/webhook"),
          filePart("certificate", "/tmp/cert.pem"),
          stringPart("ip_address", "192.168.2.1"),
          stringPart("max_connections", "25"),
          stringPart("allowed_updates", jsonResourceString("json/command/allowed-updates.json")),
          stringPart("drop_pending_updates", "true"),
          stringPart("secret_token", "token-1")
        )
      }
    }
  }
}
