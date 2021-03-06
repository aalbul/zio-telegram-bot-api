package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.GetUpdates.GetUpdatesPayload
import io.github.aalbul.zio.telegram.domain.{Update, UpdateTypes}
import io.github.aalbul.zio.telegram.test.BaseSpec
import io.circe.syntax.EncoderOps
import zio.durationInt

class GetUpdatesSpec extends BaseSpec {
  trait Scope {
    val command: Command[Seq[Update]] =
      GetUpdates
        .of
        .withOffset(5)
        .withLimit(100)
        .withTimeout(10.minutes)
        .withAllowedUpdates(Set(UpdateTypes.ChannelPost))

    val payload: GetUpdatesPayload = GetUpdatesPayload(
      offset = Some(5),
      limit = Some(100),
      timeout = Some(600),
      allowedUpdates = Some(Set(UpdateTypes.ChannelPost))
    )
  }

  "GetUpdates" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "getUpdates"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "ForwardMessagePayload" should {
      "serialize payload to json" in new Scope {
        payload.asJson shouldBe jsonResource("json/command/get-updates-payload.json")
      }
    }
  }
}
