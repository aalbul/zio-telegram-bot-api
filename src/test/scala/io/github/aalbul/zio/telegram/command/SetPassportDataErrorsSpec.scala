package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.SetPassportDataErrors.SetPassportDataErrorsPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class SetPassportDataErrorsSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = SetPassportDataErrors
      .of(userId = 52, errors = Seq(passportElementError2, passportElementError7))
    val payload: SetPassportDataErrorsPayload = SetPassportDataErrorsPayload(
      userId = 52,
      errors = Seq(passportElementError2, passportElementError7)
    )
  }

  "SetPassportDataErrors" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "setPassportDataErrors"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "SetPassportDataErrorsPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource(
            "json/command/set-passport-data-errors-payload.json"
          )
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[SetPassportDataErrorsPayload](
            "json/command/set-passport-data-errors-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
