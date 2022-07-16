package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.projection.MessageProjector.{messageProjectorMonoidK, all}
import io.github.aalbul.zio.telegram.projection.message.{TextMessage, VenueMessage}
import io.github.aalbul.zio.telegram.test.BaseSpec

class MessageProjectorSpec extends BaseSpec {
  "MessageProjector" when {
    "messageProjectorMonoidK" should {
      "produce empty message projector" in {
        val emptyProjector = messageProjectorMonoidK.empty
        allMessages.flatMap(emptyProjector.project).toList.length shouldBe 0
      }

      "combine 2 projectors" in {
        val combined =
          messageProjectorMonoidK.combineK(TextMessage.textMessageProjector, VenueMessage.venueMessageProjector)

        combined.project(textMessage1) shouldBe Some(textMessageProjection)
        combined.project(venueMessage1) shouldBe Some(venueMessageProjection)

        allMessages.diff(Set(textMessage1, venueMessage1)).flatMap(combined.project).toList.length shouldBe 0
      }
    }

    "all" should {
      "be able to decode any text message" in {
        allMessages.flatMap(MessageProjector.all.project).toList.length shouldBe allMessages.size
      }
    }
  }
}
