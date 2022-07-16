package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import DocumentMessage.documentMessageProjector

class DocumentMessageSpec extends BaseSpec {
  "DocumentMessage" when {
    "documentMessageProjector" should {
      "properly project document messages" in {
        documentMessageProjector.project(documentMessage1) shouldBe Some(documentMessageProjection)
      }

      "not match any other messages" in {
        (allMessages - documentMessage1).flatMap(documentMessageProjector.project) shouldBe empty
      }
    }
  }
}
