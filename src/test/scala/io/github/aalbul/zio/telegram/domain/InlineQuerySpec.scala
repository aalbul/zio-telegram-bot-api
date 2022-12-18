package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class InlineQuerySpec extends BaseSpec {
  "InlineQuery" when {
    "encoder" should {
      "encode inline query to json" in {
        writeToString(inlineQuery1) should matchJsonResource("json/model/inline-query.json")
      }
    }

    "decoder" should {
      "decode inline query from json" in {
        jsonResourceAs[InlineQuery]("json/model/inline-query.json") shouldBe inlineQuery1
      }
    }
  }
}
