package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.test.BaseSpec

class LogOutSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = new LogOut
  }

  "LogOut" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "logOut"
      }
    }

    "parameters" should {
      "have no parameters" in new Scope {
        command.parameters shouldBe NoParameters
      }
    }
  }
}
