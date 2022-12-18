package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class ChosenInlineResultSpec extends BaseSpec {
  "ChosenInlineResult" when {
    "encode" should {
      "encode chat photo to json" in {
        writeToString(chosenInlineResult1) should matchJsonResource("json/model/chosen-inline-result.json")
      }
    }

    "decoder" should {
      "decode chosen inline result from json" in {
        jsonResourceAs[ChosenInlineResult]("json/model/chosen-inline-result.json") shouldBe chosenInlineResult1
      }
    }
  }
}
