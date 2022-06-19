package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.domain.User

/** A simple method for testing your bot's authentication token. Requires no parameters. Returns basic information about
  * the bot in form of a [[User]] object.
  */
class GetMe extends Command[User] {
  override val name: String = "getMe"
  override def parameters: ApiParameters = NoParameters
}
