package io.github.aalbul.zio.telegram.syntax

import io.github.aalbul.zio.telegram.engine.BotEngine
import io.github.aalbul.zio.telegram.engine.BotEngine.BotException
import io.github.aalbul.zio.telegram.test.{BaseSpec, Builders}
import org.mockito.scalatest.MockitoSugar
import zio.stream.ZStream
import zio.{ULayer, ZIO, ZLayer}

class FileSyntaxSpec extends BaseSpec with Builders with MockitoSugar {
  trait Scope extends FileSyntax {
    val botEngine: BotEngine = mock[BotEngine]
    val fileContent: String = "hello world"
    val botEngineLayer: ULayer[BotEngine] = ZLayer.succeed(botEngine)
  }

  "FileSyntax" when {
    "stream" should {
      "stream file content if file path is defined" in new Scope {
        when(botEngine.streamFile(file1.filePath.get))
          .thenReturn(ZIO.succeed(ZStream.fromIterable(fileContent.getBytes)))

        file1.stream.runCollect
          .map(bytes => new String(bytes.toArray))
          .provideLayer(botEngineLayer)
          .run shouldBe fileContent
      }

      "should throw an exception when trying to stream file without file path" in new Scope {
        val exception: BotException =
          the[BotException] thrownBy file2.stream.provideLayer(botEngineLayer).runCollect.run
        exception.message shouldBe s"No file path found for file: ${file2.fileId}"
      }
    }
  }
}
