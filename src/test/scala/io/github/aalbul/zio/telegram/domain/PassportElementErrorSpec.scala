package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class PassportElementErrorSpec extends BaseSpec {
  "PassportElementError" when {
    "encoder" should {
      "should encode PassportElementErrorDataField to json" in {
        writeToString(passportElementError1) should matchJsonResource(
          "json/model/passport-element-error-data-field.json"
        )
      }

      "should encode PassportElementErrorFrontSide to json" in {
        writeToString(passportElementError2) should matchJsonResource(
          "json/model/passport-element-error-front-side.json"
        )
      }

      "should encode PassportElementErrorReverseSide to json" in {
        writeToString(passportElementError3) should matchJsonResource(
          "json/model/passport-element-error-reverse-side.json"
        )
      }

      "should encode PassportElementErrorSelfie to json" in {
        writeToString(passportElementError4) should matchJsonResource(
          "json/model/passport-element-error-selfie.json"
        )
      }

      "should encode PassportElementErrorFile to json" in {
        writeToString(passportElementError5) should matchJsonResource(
          "json/model/passport-element-error-file.json"
        )
      }

      "should encode PassportElementErrorFiles to json" in {
        writeToString(passportElementError6) should matchJsonResource(
          "json/model/passport-element-error-files.json"
        )
      }

      "should encode PassportElementErrorTranslationFile to json" in {
        writeToString(passportElementError7) should matchJsonResource(
          "json/model/passport-element-error-translation-file.json"
        )
      }

      "should encode PassportElementErrorTranslationFiles to json" in {
        writeToString(passportElementError8) should matchJsonResource(
          "json/model/passport-element-error-translation-files.json"
        )
      }

      "should encode PassportElementErrorUnspecified to json" in {
        writeToString(passportElementError9) should matchJsonResource(
          "json/model/passport-element-error-unspecified.json"
        )
      }
    }

    "decoder" should {
      "should decode PassportElementErrorDataField from json" in {
        jsonResourceAs[PassportElementError](
          "json/model/passport-element-error-data-field.json"
        ) shouldBe passportElementError1
      }

      "should decode PassportElementErrorFrontSide from json" in {
        jsonResourceAs[PassportElementError](
          "json/model/passport-element-error-front-side.json"
        ) shouldBe passportElementError2
      }

      "should decode PassportElementErrorReverseSide from json" in {
        jsonResourceAs[PassportElementError](
          "json/model/passport-element-error-reverse-side.json"
        ) shouldBe passportElementError3
      }

      "should decode PassportElementErrorSelfie from json" in {
        jsonResourceAs[PassportElementError](
          "json/model/passport-element-error-selfie.json"
        ) shouldBe passportElementError4
      }

      "should decode PassportElementErrorFile from json" in {
        jsonResourceAs[PassportElementError](
          "json/model/passport-element-error-file.json"
        ) shouldBe passportElementError5
      }

      "should decode PassportElementErrorFiles from json" in {
        jsonResourceAs[PassportElementError](
          "json/model/passport-element-error-files.json"
        ) shouldBe passportElementError6
      }

      "should decode PassportElementErrorTranslationFile from json" in {
        jsonResourceAs[PassportElementError](
          "json/model/passport-element-error-translation-file.json"
        ) shouldBe passportElementError7
      }

      "should decode PassportElementErrorTranslationFiles from json" in {
        jsonResourceAs[PassportElementError](
          "json/model/passport-element-error-translation-files.json"
        ) shouldBe passportElementError8
      }

      "should decode PassportElementErrorUnspecified from json" in {
        jsonResourceAs[PassportElementError](
          "json/model/passport-element-error-unspecified.json"
        ) shouldBe passportElementError9
      }
    }
  }
}
