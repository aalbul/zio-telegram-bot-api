package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class WebhookInfoSpec extends BaseSpec {
  "WebhookInfo" when {
    "encoder" should {
      "encode webhook info to json" in {
        writeToString(webhookInfo1) should matchJsonResource("json/model/webhook-info.json")
      }
    }

    "decoder" should {
      "decode webhook info from json" in {
        jsonResourceAs[WebhookInfo]("json/model/webhook-info.json") shouldBe webhookInfo1
      }
    }
  }
}
