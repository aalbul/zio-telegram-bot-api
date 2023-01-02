package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class WriteAccessAllowedSpec extends BaseSpec {
  "WriteAccessAllowed" when {
    "encoder" should {
      "encode write access allowed to json" in {
        writeToString(writeAccessAllowed1) should matchJsonResource("json/model/write-access-allowed.json")
      }
    }

    "decoder" should {
      "decode write access allowed from json" in {
        jsonResourceAs[WriteAccessAllowed](
          "json/model/write-access-allowed.json"
        ) shouldBe writeAccessAllowed1
      }
    }
  }
}
