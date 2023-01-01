package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.FileDescriptor.pathDescriptor
import io.github.aalbul.zio.telegram.command.MultipartBody.{filePart, stringPart}
import io.github.aalbul.zio.telegram.domain.StickerType
import io.github.aalbul.zio.telegram.test.BaseSpec

class CreateNewStickerSetSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] =
      CreateNewStickerSet
        .of(userId = 551, name = "sticker-one", title = "sticker one", emojis = "😀")
        .withPngSticker(pathDescriptor("/tmp/sticker.png"))
        .withTgsSticker(pathDescriptor("/tmp/sticker.tgs"))
        .withWebmSticker(pathDescriptor("/tmp/sticker.webm"))
        .withStickerType(StickerType.Regular)
        .withMaskPosition(maskPosition1)
  }

  "CreateNewStickerSet" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "createNewStickerSet"
      }
    }

    "parameters" should {
      "represent parameters as form data" in new Scope {
        command.parameters shouldBe MultipartBody.of(
          stringPart("user_id", "551"),
          stringPart("name", "sticker-one"),
          stringPart("title", "sticker one"),
          filePart("png_sticker", "/tmp/sticker.png"),
          filePart("tgs_sticker", "/tmp/sticker.tgs"),
          filePart("webm_sticker", "/tmp/sticker.webm"),
          stringPart("sticker_type", "regular"),
          stringPart("emojis", "😀"),
          stringPart("mask_position", jsonResourceString("json/command/mask-position.json")),
        )
      }
    }
  }
}
