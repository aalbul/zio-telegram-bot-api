package io.github.aalbul.zio.telegram.engine

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.codecs.string
import io.github.aalbul.zio.telegram.engine.ApiResponse.*
import io.github.aalbul.zio.telegram.test.BaseSpec

class ApiResponseSpec extends BaseSpec {

  "ApiResponse" when {
    "encoder" should {
      "encode failure responses" in {
        writeToString[ApiResponse[String]](failureApiResponse1) should matchJsonResource(
          "json/engine/error-response.json"
        )
      }

      "encode success responses" in {
        writeToString[ApiResponse[String]](successApiResponse1) should matchJsonResource(
          "json/engine/success-response.json"
        )
      }
    }

    "decoder" should {
      "decode failure responses" in {
        jsonResourceAs[ApiResponse[String]]("json/engine/error-response.json") shouldBe failureApiResponse1
      }

      "decode success responses" in {
        jsonResourceAs[ApiResponse[String]]("json/engine/success-response.json") shouldBe successApiResponse1
      }
    }
  }
}
