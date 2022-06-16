package io.github.aalbul.zio.telegram.test

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import zio.{RIO, Runtime as ZRuntime}

trait BaseSpec extends AnyWordSpec with Matchers with Builders with JsonTestingSupport {
  val zRuntime: ZRuntime[Any] = ZRuntime.default

  implicit class RIOOps[A](task: RIO[Any, A]) {
    def run: A = zRuntime.unsafeRunTask[A](task)
  }
}
