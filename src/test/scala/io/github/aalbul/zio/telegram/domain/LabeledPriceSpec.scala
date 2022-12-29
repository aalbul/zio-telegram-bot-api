package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class LabeledPriceSpec extends BaseSpec {
  "LabeledPrice" when {
    "encoder" should {
      "encode labeled price to json" in {
        writeToString(labeledPrice1) should matchJsonResource("json/model/labeled-price.json")
      }
    }

    "decoder" should {
      "decode labeled price from json" in {
        jsonResourceAs[LabeledPrice]("json/model/labeled-price.json") shouldBe labeledPrice1
      }
    }
  }
}
