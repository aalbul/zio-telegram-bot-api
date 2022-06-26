package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.test.BaseSpec

class CloseSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = new Close
  }

  "Close" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "close"
      }
    }

    "parameters" should {
      "have no parameters" in new Scope {
        command.parameters shouldBe NoParameters
      }
    }
  }
}
