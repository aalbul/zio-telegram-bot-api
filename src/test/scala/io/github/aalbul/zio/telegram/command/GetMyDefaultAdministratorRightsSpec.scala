package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.GetMyDefaultAdministratorRights.GetMyDefaultAdministratorRightsPayload
import io.github.aalbul.zio.telegram.domain.ChatAdministratorRights
import io.github.aalbul.zio.telegram.test.BaseSpec

class GetMyDefaultAdministratorRightsSpec extends BaseSpec {
  trait Scope {
    val command: Command[ChatAdministratorRights] = GetMyDefaultAdministratorRights
      .of
      .withForChannels(true)
    val payload: GetMyDefaultAdministratorRightsPayload = GetMyDefaultAdministratorRightsPayload(
      forChannels = Some(true)
    )
  }

  "GetMyDefaultAdministratorRights" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "getMyDefaultAdministratorRights"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "GetMyDefaultAdministratorRightsPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource(
            "json/command/get-my-default-administrator-rights-payload.json"
          )
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[GetMyDefaultAdministratorRightsPayload](
            "json/command/get-my-default-administrator-rights-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
