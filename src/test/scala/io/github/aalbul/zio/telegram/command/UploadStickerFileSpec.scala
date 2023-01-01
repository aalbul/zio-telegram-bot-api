package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.FileDescriptor.pathDescriptor
import io.github.aalbul.zio.telegram.command.MultipartBody.{filePart, stringPart}
import io.github.aalbul.zio.telegram.domain.File
import io.github.aalbul.zio.telegram.test.BaseSpec

class UploadStickerFileSpec extends BaseSpec {
  trait Scope {
    val command: Command[File] = UploadStickerFile.of(userId = 557, pngSticker = pathDescriptor("/tmp/sticker.png"))
  }

  "UploadStickerFile" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "uploadStickerFile"
      }
    }

    "parameters" should {
      "represent parameters as form data" in new Scope {
        command.parameters shouldBe MultipartBody.of(
          stringPart("user_id", "557"),
          filePart("png_sticker", "/tmp/sticker.png")
        )
      }
    }
  }
}
