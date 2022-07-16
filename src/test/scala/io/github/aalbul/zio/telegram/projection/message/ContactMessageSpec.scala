package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import ContactMessage.contactMessageProjector

class ContactMessageSpec extends BaseSpec {
  "ContactMessage" when {
    "contactMessageProjector" should {
      "properly project contact messages" in {
        contactMessageProjector.project(contactMessage1) shouldBe Some(contactMessageProjection)
      }

      "not match any other messages" in {
        (allMessages - contactMessage1).flatMap(contactMessageProjector.project) shouldBe empty
      }
    }
  }
}
