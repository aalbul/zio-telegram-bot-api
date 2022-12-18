package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class UpdateSpec extends BaseSpec {
  "Update" when {
    "encoder" should {
      "encode update to json" in {
        writeToString(fullUpdate1) should matchJsonResource("json/model/full-update.json")
      }
    }

    "decoder" should {
      "decode update from json" in {
        jsonResourceAs[Update]("json/model/full-update.json") shouldBe fullUpdate1
      }
    }
  }
}
