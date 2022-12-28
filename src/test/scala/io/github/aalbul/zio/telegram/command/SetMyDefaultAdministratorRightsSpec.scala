package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.SetMyDefaultAdministratorRights.SetMyDefaultAdministratorRightsPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class SetMyDefaultAdministratorRightsSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = SetMyDefaultAdministratorRights
      .of
      .withRights(chatAdministratorRights1)
      .withForChannels(true)
    val payload: SetMyDefaultAdministratorRightsPayload = SetMyDefaultAdministratorRightsPayload(
      rights = Some(chatAdministratorRights1),
      forChannels = Some(true)
    )
  }

  "SetMyDefaultAdministratorRights" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "setMyDefaultAdministratorRights"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "SetMyDefaultAdministratorRightsPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource(
            "json/command/set-my-default-administrator-rights-payload.json"
          )
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[SetMyDefaultAdministratorRightsPayload](
            "json/command/set-my-default-administrator-rights-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
