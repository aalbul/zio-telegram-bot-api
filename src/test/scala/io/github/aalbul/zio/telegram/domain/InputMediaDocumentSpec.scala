package io.github.aalbul.zio.telegram.domain

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.test.BaseSpec

class InputMediaDocumentSpec extends BaseSpec {
  "InputMediaDocument" when {
    "encoder" should {
      "should encode input media document to json" in {
        inputMediaDocument1.asJson shouldBe jsonResource("json/model/input-media-document.json")
      }
    }
  }
}
