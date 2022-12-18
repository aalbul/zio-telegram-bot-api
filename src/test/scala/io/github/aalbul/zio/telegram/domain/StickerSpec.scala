package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class StickerSpec extends BaseSpec {
  "Sticker" when {
    "encoder" should {
      "encode sticker to json" in {
        writeToString(sticker1) should matchJsonResource("json/model/sticker.json")
      }
    }

    "decoder" should {
      "decode sticker from json" in {
        jsonResourceAs[Sticker]("json/model/sticker.json") shouldBe sticker1
      }
    }
  }
}
