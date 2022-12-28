package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.serialization.DefaultJsonCodecs

import java.time.Instant

object WebhookInfo extends DefaultJsonCodecs {
  implicit val webhookInfoJsonCodec: JsonValueCodec[WebhookInfo] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[WebhookInfo]]
    * @param url
    *   Webhook URL, may be empty if webhook is not set up
    * @param hasCustomCertificate
    *   True, if a custom certificate was provided for webhook certificate checks
    * @param pendingUpdateCount
    *   Number of updates awaiting delivery
    * @return
    *   [[WebhookInfo]] builder
    */
  def of(url: String, hasCustomCertificate: Boolean, pendingUpdateCount: Long): WebhookInfo = WebhookInfo(
    url = url,
    hasCustomCertificate = hasCustomCertificate,
    pendingUpdateCount = pendingUpdateCount,
    ipAddress = None,
    lastErrorDate = None,
    lastErrorMessage = None,
    lastSynchronizationErrorDate = None,
    maxConnections = None,
    allowedUpdates = None
  )
}

/** Describes the current status of a webhook.
  */
case class WebhookInfo(
  url: String,
  hasCustomCertificate: Boolean,
  pendingUpdateCount: Long,
  ipAddress: Option[String],
  lastErrorDate: Option[Instant],
  lastErrorMessage: Option[String],
  lastSynchronizationErrorDate: Option[Instant],
  maxConnections: Option[Long],
  allowedUpdates: Option[Set[UpdateType]]
) {

  /** Currently used webhook IP address
    */
  def withIpAddress(ipAddress: String): WebhookInfo = copy(ipAddress = Some(ipAddress))

  /** Unix time for the most recent error that happened when trying to deliver an update via webhook
    */
  def withLastErrorDate(lastErrorDate: Instant): WebhookInfo = copy(lastErrorDate = Some(lastErrorDate))

  /** Error message in human-readable format for the most recent error that happened when trying to deliver an update
    * via webhook
    */
  def withLastErrorMessage(lastErrorMessage: String): WebhookInfo = copy(lastErrorMessage = Some(lastErrorMessage))

  /** Unix time of the most recent error that happened when trying to synchronize available updates with Telegram
    * datacenters
    */
  def withLastSynchronizationErrorDate(lastSynchronizationErrorDate: Instant): WebhookInfo =
    copy(lastSynchronizationErrorDate = Some(lastSynchronizationErrorDate))

  /** The maximum allowed number of simultaneous HTTPS connections to the webhook for update delivery
    */
  def withMaxConnections(maxConnections: Long): WebhookInfo = copy(maxConnections = Some(maxConnections))

  /** A list of update types the bot is subscribed to. Defaults to all update types except chat_member
    */
  def withAllowedUpdates(allowedUpdates: Set[UpdateType]): WebhookInfo = copy(allowedUpdates = Some(allowedUpdates))
}
