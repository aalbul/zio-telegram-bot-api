package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class MessageAutoDeleteTimerChangedSpec extends BaseSpec {
  "MessageAutoDeleteTimerChanged" when {
    "decoder" should {
      "decode message auto delete timer changed json" in {
        jsonResourceAs[MessageAutoDeleteTimerChanged](
          "json/model/message-auto-delete-timer-changed.json"
        ) shouldBe messageAutoDeleteTimerChanged1
      }
    }
  }
}
