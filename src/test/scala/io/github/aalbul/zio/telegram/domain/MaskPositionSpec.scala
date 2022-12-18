package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class MaskPositionSpec extends BaseSpec {
  "MaskPosition" when {
    "encoder" should {
      "encode mask position to json" in {
        writeToString(maskPosition1) should matchJsonResource("json/model/mask-position.json")
      }
    }

    "decoder" should {
      "decode mask position from json" in {
        jsonResourceAs[MaskPosition]("json/model/mask-position.json") shouldBe maskPosition1
      }
    }
  }
}
