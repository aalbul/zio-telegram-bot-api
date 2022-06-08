package com.github.aalbul.zio.telegram.command

import com.github.aalbul.zio.telegram.domain.User
import com.github.aalbul.zio.telegram.test.BaseSpec

class GetMeSpec extends BaseSpec {
  trait Scope {
    val command: Command[User] = new GetMe
  }

  "GetMe" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "getMe"
      }
    }

    "parameters" should {
      "have no parameters" in new Scope {
        command.parameters shouldBe NoParameters
      }
    }
  }
}
