package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.domain.Sticker
import io.github.aalbul.zio.telegram.test.BaseSpec

class GetForumTopicIconStickersSpec extends BaseSpec {
  trait Scope {
    val command: Command[Seq[Sticker]] = GetForumTopicIconStickers.of()
  }

  "GetForumTopicIconStickers" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "getForumTopicIconStickers"
      }
    }

    "parameters" should {
      "have no parameters" in new Scope {
        command.parameters shouldBe NoParameters
      }
    }
  }
}
