package io.github.aalbul.zio.telegram.domain

import io.circe.Json
import io.github.aalbul.zio.telegram.domain.StickerTypes.*
import io.github.aalbul.zio.telegram.test.BaseSpec

class StickerTypesSpec extends BaseSpec {
  "StickerTypes" when {
    "decoder" should {
      "decode sticker type from json" in {
        Json.fromString("custom_emoji").as[StickerType] shouldBe Right(StickerTypes.CustomEmoji)
        Json.fromString("regular").as[StickerType] shouldBe Right(StickerTypes.Regular)
      }
    }
  }
}
