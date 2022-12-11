package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class FileSpec extends BaseSpec {
  "File" when {
    "decoder" should {
      "should decode file json" in {
        jsonResourceAs[File]("json/model/file.json") shouldBe file1
      }
    }
  }
}
