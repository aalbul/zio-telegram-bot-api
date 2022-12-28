package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object Close {

  /** Constructs minimal [[Close]] command
    * @return
    *   [[Close]] builder
    */
  def of: Close = new Close
}

/** Use this method to close the bot instance before moving it from one local server to another. You need to delete the
  * webhook before calling this method to ensure that the bot isn't launched again after server restart. The method will
  * return error ''429'' in the first 10 minutes after the bot is launched. Returns ''True'' on success. Requires no
  * parameters.
  */
class Close extends Command[Boolean] {
  override val name: String = "close"
  override def parameters: ApiParameters = NoParameters
}
