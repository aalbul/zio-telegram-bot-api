package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class DocumentSpec extends BaseSpec {
  "Document" when {
    "encode" should {
      "encode document to json" in {
        writeToString(document1) should matchJsonResource("json/model/document.json")
      }
    }

    "decoder" should {
      "decode document from json" in {
        jsonResourceAs[Document]("json/model/document.json") shouldBe document1
      }
    }
  }
}
