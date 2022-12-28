package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.GetFile.GetFilePayload
import io.github.aalbul.zio.telegram.domain.File
import io.github.aalbul.zio.telegram.test.BaseSpec

class GetFileSpec extends BaseSpec {
  trait Scope {
    val command: Command[File] = GetFile.of(fileId = "99112")
    val payload: GetFilePayload = GetFilePayload(fileId = "99112")
  }

  "GetFile" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "getFile"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "GetFilePayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/get-file-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[GetFilePayload]("json/command/get-file-payload.json") shouldBe payload
        }
      }
    }
  }
}
