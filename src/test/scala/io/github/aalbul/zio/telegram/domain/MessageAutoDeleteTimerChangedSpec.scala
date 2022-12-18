package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class MessageAutoDeleteTimerChangedSpec extends BaseSpec {
  "MessageAutoDeleteTimerChanged" when {
    "encoder" should {
      "encode message auto delete timer changed to json" in {
        writeToString(messageAutoDeleteTimerChanged1) should matchJsonResource(
          "json/model/message-auto-delete-timer-changed.json"
        )
      }
    }

    "decoder" should {
      "decode message auto delete timer changed from json" in {
        jsonResourceAs[MessageAutoDeleteTimerChanged](
          "json/model/message-auto-delete-timer-changed.json"
        ) shouldBe messageAutoDeleteTimerChanged1
      }
    }
  }
}
