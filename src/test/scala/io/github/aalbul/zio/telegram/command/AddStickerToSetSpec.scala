package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.FileDescriptor.pathDescriptor
import io.github.aalbul.zio.telegram.command.MultipartBody.{filePart, stringPart}
import io.github.aalbul.zio.telegram.test.BaseSpec

class AddStickerToSetSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] =
      AddStickerToSet
        .of(userId = 552, name = "sticker-one", emojis = "😀")
        .withPngSticker(pathDescriptor("/tmp/sticker.png"))
        .withTgsSticker(pathDescriptor("/tmp/sticker.tgs"))
        .withWebmSticker(pathDescriptor("/tmp/sticker.webm"))
        .withMaskPosition(maskPosition1)
  }

  "AddStickerToSet" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "addStickerToSet"
      }
    }

    "parameters" should {
      "represent parameters as form data" in new Scope {
        command.parameters shouldBe MultipartBody.of(
          stringPart("user_id", "552"),
          stringPart("name", "sticker-one"),
          filePart("png_sticker", "/tmp/sticker.png"),
          filePart("tgs_sticker", "/tmp/sticker.tgs"),
          filePart("webm_sticker", "/tmp/sticker.webm"),
          stringPart("emojis", "😀"),
          stringPart("mask_position", jsonResourceString("json/command/mask-position.json"))
        )
      }
    }
  }
}
