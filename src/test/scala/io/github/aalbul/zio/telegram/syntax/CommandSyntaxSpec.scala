package io.github.aalbul.zio.telegram.syntax

import io.github.aalbul.zio.telegram.command.Command
import io.github.aalbul.zio.telegram.domain.Message
import io.github.aalbul.zio.telegram.engine.BotEngine
import io.github.aalbul.zio.telegram.test.BaseSpec
import org.mockito.scalatest.MockitoSugar
import zio.{ULayer, ZIO, ZLayer}

class CommandSyntaxSpec extends BaseSpec with MockitoSugar {
  trait Scope extends CommandSyntax {
    val command: Command[Message] = mock[Command[Message]]
    val botEngine: BotEngine = mock[BotEngine]
    val message: Message = mock[Message]
    val botEngineLayer: ULayer[BotEngine] = ZLayer.succeed(botEngine)

    when(botEngine.execute(command)).thenReturn(ZIO.succeed(message))
  }

  "CommandSyntax" when {
    "CommandOps" when {
      "execute" should {
        "delegate command execute call to bot engine" in new Scope {
          command.execute.provideLayer(botEngineLayer).run shouldBe message
        }
      }
    }

    "commandAsZIO" should {
      "implicitly transform command to executable zio effect" in new Scope {
        (for {
          message1 <- command
          message2 <- command
        } yield (message1, message2)).provideLayer(botEngineLayer).run shouldBe (message, message)
      }
    }
  }
}
