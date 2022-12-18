package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class FileSpec extends BaseSpec {
  "File" when {
    "encode" should {
      "encode file to json" in {
        writeToString(file1) should matchJsonResource("json/model/file.json")
      }
    }

    "decoder" should {
      "decode file from json" in {
        jsonResourceAs[File]("json/model/file.json") shouldBe file1
      }
    }
  }
}
