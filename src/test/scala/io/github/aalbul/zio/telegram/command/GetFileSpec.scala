package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.File
import io.github.aalbul.zio.telegram.test.BaseSpec

class GetFileSpec extends BaseSpec {
  trait Scope {
    val command: Command[File] = GetFile.of("99112")
  }

  "GetFile" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "getFile"
      }
    }

    "parameters" should {
      "represent parameters as form data" in new Scope {
        command.parameters shouldBe MultipartBody.of(stringPart("file_id", "99112"))
      }
    }
  }
}
