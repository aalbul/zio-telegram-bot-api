package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.FileDescriptor.{fileDescriptorEncoder, idDescriptor, pathDescriptor, urlDescriptor}
import io.github.aalbul.zio.telegram.test.BaseSpec
import io.circe.Json

class FileDescriptorSpec extends BaseSpec {
  "FileDescriptor" when {
    "fileDescriptorEncoder" should {
      "encode PathBasedFileDescriptor" in {
        fileDescriptorEncoder.apply(pathDescriptor("/tmp/test.txt")) shouldBe Json.fromString("attach:///tmp/test.txt")
      }

      "encode UrlFileDescriptor" in {
        fileDescriptorEncoder.apply(urlDescriptor("https://google.com")) shouldBe Json.fromString("https://google.com")
      }

      "encode IdFileDescriptor" in {
        fileDescriptorEncoder.apply(idDescriptor("5843")) shouldBe Json.fromString("5843")
      }
    }
  }
}
