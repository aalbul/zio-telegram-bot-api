package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.UpdateType

object SetWebhook {

  /** Constructs minimal [[SetWebhook]] command
    * @param url
    *   HTTPS URL to send updates to. Use an empty string to remove webhook integration
    * @return
    *   [[SetWebhook]] builder
    */
  def of(url: String): SetWebhook = SetWebhook(
    url = url,
    certificate = None,
    ipAddress = None,
    maxConnections = None,
    allowedUpdates = None,
    dropPendingUpdates = None,
    secretToken = None
  )
}

/** Use this method to specify a URL and receive incoming updates via an outgoing webhook. Whenever there is an update
  * for the bot, we will send an HTTPS POST request to the specified URL, containing a JSON-serialized
  * [[https://core.telegram.org/bots/api#update Update]]. In case of an unsuccessful request, we will give up after a
  * reasonable amount of attempts. Returns True on success.
  *
  * If you'd like to make sure that the webhook was set by you, you can specify secret data in the parameter
  * secret_token. If specified, the request will contain a header “X-Telegram-Bot-Api-Secret-Token” with the secret
  * token as content.
  *
  * Notes
  *
  *   - \1. You will not be able to receive updates using [[https://core.telegram.org/bots/api#getupdates getUpdates]]
  *     for as long as an outgoing webhook is set up.
  *   - 2. To use a self-signed certificate, you need to upload your
  *     [[https://core.telegram.org/bots/self-signed public key certificate]] using certificate parameter. Please upload
  *     as InputFile, sending a String will not work.
  *   - 3. Ports currently supported for webhooks: 443, 80, 88, 8443.
  *
  * If you're having any trouble setting up webhooks, please check out this
  * [[https://core.telegram.org/bots/webhooks amazing guide to webhooks]].
  */
case class SetWebhook(
  url: String,
  certificate: Option[FileDescriptor],
  ipAddress: Option[String],
  maxConnections: Option[Long],
  allowedUpdates: Option[Set[UpdateType]],
  dropPendingUpdates: Option[Boolean],
  secretToken: Option[String]
) extends Command[Boolean] {
  override val name: String = "setWebhook"
  override def parameters: ApiParameters = MultipartBody.ofOpt(
    Some(stringPart("url", url)),
    certificate.map(_.asMultipart("certificate")),
    ipAddress.map(stringPart("ip_address", _)),
    maxConnections.map(stringPart("max_connections", _)),
    allowedUpdates.map(stringPart("allowed_updates", _)),
    dropPendingUpdates.map(stringPart("drop_pending_updates", _)),
    secretToken.map(stringPart("secret_token", _)),
  )

  /** Upload your public key certificate so that the root certificate in use can be checked. See our
    * [[https://core.telegram.org/bots/self-signed self-signed guide]] for details.
    */
  def withCertificate(certificate: FileDescriptor): SetWebhook = copy(certificate = Some(certificate))

  /** The fixed IP address which will be used to send webhook requests instead of the IP address resolved through DNS
    */
  def withIpAddress(ipAddress: String): SetWebhook = copy(ipAddress = Some(ipAddress))

  /** The maximum allowed number of simultaneous HTTPS connections to the webhook for update delivery, 1-100. Defaults
    * to 40. Use lower values to limit the load on your bot's server, and higher values to increase your bot's
    * throughput.
    */
  def withMaxConnections(maxConnections: Long): SetWebhook = copy(maxConnections = Some(maxConnections))

  /** A JSON-serialized list of the update types you want your bot to receive. For example, specify [“message”,
    * “edited_channel_post”, “callback_query”] to only receive updates of these types. See
    * [[https://core.telegram.org/bots/api#update Update]] for a complete list of available update types. Specify an
    * empty list to receive all update types except chat_member (default). If not specified, the previous setting will
    * be used. Please note that this parameter doesn't affect updates created before the call to the setWebhook, so
    * unwanted updates may be received for a short period of time.
    */
  def withAllowedUpdates(allowedUpdates: Set[UpdateType]): SetWebhook = copy(allowedUpdates = Some(allowedUpdates))

  /** Pass True to drop all pending updates
    */
  def withDropPendingUpdates(dropPendingUpdates: Boolean): SetWebhook =
    copy(dropPendingUpdates = Some(dropPendingUpdates))

  /** A secret token to be sent in a header “X-Telegram-Bot-Api-Secret-Token” in every webhook request, 1-256
    * characters. Only characters A-Z, a-z, 0-9, _ and - are allowed. The header is useful to ensure that the request
    * comes from a webhook set by you.
    */
  def withSecretToken(secretToken: String): SetWebhook = copy(secretToken = Some(secretToken))
}
