package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.FileDescriptor.pathDescriptor
import io.github.aalbul.zio.telegram.command.MultipartBody.{filePart, stringPart}
import io.github.aalbul.zio.telegram.test.BaseSpec

class SetStickerSetThumbSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] =
      SetStickerSetThumb
        .of(userId = 552, name = "sticker-one")
        .withThumb(pathDescriptor("/tmp/sticker_thumb.png"))
  }

  "SetStickerSetThumb" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "setStickerSetThumb"
      }
    }

    "parameters" should {
      "represent parameters as form data" in new Scope {
        command.parameters shouldBe MultipartBody.of(
          stringPart("name", "sticker-one"),
          stringPart("user_id", "552"),
          filePart("thumb", "/tmp/sticker_thumb.png")
        )
      }
    }
  }
}
