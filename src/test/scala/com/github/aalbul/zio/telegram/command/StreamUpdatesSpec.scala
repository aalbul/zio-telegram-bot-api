package com.github.aalbul.zio.telegram.command

import com.github.aalbul.zio.telegram.domain.Update
import com.github.aalbul.zio.telegram.engine.BotEngine
import com.github.aalbul.zio.telegram.test.BaseSpec
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.when
import org.scalatestplus.mockito.MockitoSugar
import zio.{Chunk, ULayer, ZIO, ZLayer}

class StreamUpdatesSpec extends BaseSpec with MockitoSugar {
  trait Scope {
    val botEngine: BotEngine = mock[BotEngine]
    val botEngineLayer: ULayer[BotEngine] = ZLayer.succeed(botEngine)
  }

  "StreamUpdates" when {
    "apply" should {
      "return a stream of updates" in new Scope {
        when(botEngine.execute(any[Command[Seq[Update]]])(any))
          .thenReturn(ZIO.succeed(Seq(update1, update2)), ZIO.succeed(Seq(update3)), ZIO.succeed(Nil))
        StreamUpdates(chunkSize = 2).take(3).runCollect.provideLayer(botEngineLayer).run shouldBe Chunk(
          update1,
          update2,
          update3
        )
      }
    }
  }
}
