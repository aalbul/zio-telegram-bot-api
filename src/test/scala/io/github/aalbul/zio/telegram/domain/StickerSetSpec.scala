package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class StickerSetSpec extends BaseSpec {
  "StickerSet" when {
    "encoder" should {
      "encode sticker set to json" in {
        writeToString(stickerSet1) should matchJsonResource("json/model/sticker-set.json")
      }
    }

    "decoder" should {
      "decode sticker set from json" in {
        jsonResourceAs[StickerSet]("json/model/sticker-set.json") shouldBe stickerSet1
      }
    }
  }
}
