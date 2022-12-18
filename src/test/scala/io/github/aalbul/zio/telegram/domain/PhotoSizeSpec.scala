package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class PhotoSizeSpec extends BaseSpec {
  "PhotoSize" when {
    "encoder" should {
      "encode photo size to json" in {
        writeToString(photoSize1) should matchJsonResource("json/model/photo-size.json")
      }
    }

    "decoder" should {
      "decode photo size from json" in {
        jsonResourceAs[PhotoSize]("json/model/photo-size.json") shouldBe photoSize1
      }
    }
  }
}
