package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.domain.Update
import io.github.aalbul.zio.telegram.engine.BotEngine
import io.github.aalbul.zio.telegram.test.BaseSpec
import org.mockito.scalatest.MockitoSugar
import zio.{Chunk, ULayer, ZIO, ZLayer}

class StreamUpdatesSpec extends BaseSpec with MockitoSugar {
  trait Scope {
    val botEngine: BotEngine = mock[BotEngine]
    val botEngineLayer: ULayer[BotEngine] = ZLayer.succeed(botEngine)
  }

  "StreamUpdates" when {
    "apply" should {
      "return a stream of updates" in new Scope {
        when(botEngine.execute(any[Command[Seq[Update]]])(any)).thenReturn(
          ZIO.succeed(Seq(updateTextMessage1, updateVoiceMessage1)),
          ZIO.succeed(Seq(updateAudioMessage1)),
          ZIO.succeed(Nil)
        )
        StreamUpdates(chunkSize = 2).take(3).runCollect.provideLayer(botEngineLayer).run shouldBe Chunk(
          updateTextMessage1,
          updateVoiceMessage1,
          updateAudioMessage1
        )
      }
    }
  }
}
