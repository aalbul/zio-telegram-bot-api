package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class DocumentSpec extends BaseSpec {
  "Document" when {
    "decoder" should {
      "should decode document json" in {
        jsonResourceAs[Document]("json/model/document.json") shouldBe document1
      }
    }
  }
}
