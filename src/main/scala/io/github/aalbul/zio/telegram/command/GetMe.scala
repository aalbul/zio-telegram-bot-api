package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.domain.User

class GetMe extends Command[User] {
  override val name: String = "getMe"
  override def parameters: ApiParameters = NoParameters
}
