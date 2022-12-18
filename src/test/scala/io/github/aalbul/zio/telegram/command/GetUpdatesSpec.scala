package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.ForwardMessage.ForwardMessagePayload
import io.github.aalbul.zio.telegram.command.GetUpdates.GetUpdatesPayload
import io.github.aalbul.zio.telegram.domain.{Update, UpdateType}
import io.github.aalbul.zio.telegram.test.BaseSpec
import zio.durationInt

class GetUpdatesSpec extends BaseSpec {
  trait Scope {
    val command: Command[Seq[Update]] =
      GetUpdates.of
        .withOffset(5)
        .withLimit(100)
        .withTimeout(10.minutes)
        .withAllowedUpdates(Set(UpdateType.ChannelPost))

    val payload: GetUpdatesPayload = GetUpdatesPayload(
      offset = Some(5),
      limit = Some(100),
      timeout = Some(600),
      allowedUpdates = Some(Set(UpdateType.ChannelPost))
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

    "GetUpdatesPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/get-updates-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[GetUpdatesPayload]("json/command/get-updates-payload.json") shouldBe payload
        }
      }
    }
  }
}
