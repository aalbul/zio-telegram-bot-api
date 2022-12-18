package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.{readFromString, writeToString}
import io.github.aalbul.zio.telegram.domain.StickerType.*
import io.github.aalbul.zio.telegram.test.BaseSpec

class StickerTypeSpec extends BaseSpec {
  "StickerType" when {
    "encoder" should {
      "encode sticker type to json" in {
        writeToString[StickerType](StickerType.CustomEmoji) shouldBe "\"custom_emoji\""
        writeToString[StickerType](StickerType.Regular) shouldBe "\"regular\""
      }
    }

    "decoder" should {
      "decode sticker type from json" in {
        readFromString[StickerType]("\"custom_emoji\"") shouldBe StickerType.CustomEmoji
        readFromString[StickerType]("\"regular\"") shouldBe StickerType.Regular
      }
    }
  }
}
