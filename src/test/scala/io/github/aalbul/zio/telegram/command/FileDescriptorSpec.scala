package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.{readFromString, writeToString}
import io.github.aalbul.zio.telegram.command.FileDescriptor.{idDescriptor, pathDescriptor, urlDescriptor}
import io.github.aalbul.zio.telegram.test.BaseSpec

class FileDescriptorSpec extends BaseSpec {
  "FileDescriptor" when {
    "encoder" should {
      "encode PathBasedFileDescriptor to json" in {
        writeToString(pathDescriptor("/tmp/test.txt")) shouldBe "\"attach:///tmp/test.txt\""
      }

      "encode UrlFileDescriptor to json" in {
        writeToString(urlDescriptor("https://google.com")) shouldBe "\"https://google.com\""
      }

      "encode IdFileDescriptor to json" in {
        writeToString(idDescriptor("5843")) shouldBe "\"5843\""
      }
    }

    "decoder" should {
      "decode PathBasedFileDescriptor from json" in {
        readFromString[FileDescriptor]("\"attach:///tmp/test.txt\"") shouldBe pathDescriptor("/tmp/test.txt")
      }

      "decode UrlFileDescriptor from json" in {
        readFromString[FileDescriptor]("\"https://google.com\"") shouldBe urlDescriptor("https://google.com")
      }

      "decode IdFileDescriptor from json" in {
        readFromString[FileDescriptor]("\"5843\"") shouldBe idDescriptor("5843")
      }
    }
  }
}
