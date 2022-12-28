package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.domain.WebhookInfo

object GetWebhookInfo {

  /** Constructs minimal [[GetWebhookInfo]] command
    * @return
    *   [[GetWebhookInfo]] builder
    */
  def of: GetWebhookInfo = new GetWebhookInfo
}

/** Use this method to get current webhook status. Requires no parameters. On success, returns a
  * [[https://core.telegram.org/bots/api#webhookinfo WebhookInfo]] object. If the bot is using
  * [[https://core.telegram.org/bots/api#getupdates getUpdates]], will return an object with the url field empty.
  */
class GetWebhookInfo extends Command[WebhookInfo] {
  override val name: String = "getWebhookInfo"
  override def parameters: ApiParameters = NoParameters
}
