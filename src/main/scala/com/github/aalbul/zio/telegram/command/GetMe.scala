package com.github.aalbul.zio.telegram.command

import com.github.aalbul.zio.telegram.domain.User

class GetMe extends Command[User] {
  override val name: String = "getMe"
  override def parameters: ApiParameters = NoParameters
}
