package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.GetUpdates.GetUpdatesPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.{Update, UpdateType}

import java.time.Duration
import java.time.temporal.ChronoUnit.SECONDS

object GetUpdates {
  object GetUpdatesPayload {
    implicit val getUpdatesPayloadJsonCodec: JsonValueCodec[GetUpdatesPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class GetUpdatesPayload(
    offset: Option[Long],
    limit: Option[Long],
    timeout: Option[Long],
    allowedUpdates: Option[Set[UpdateType]]
  )

  /** Constructs minimal [[GetUpdates]] command
    * @return
    *   [[GetUpdates]] builder
    */
  def of: GetUpdates = GetUpdates(
    GetUpdatesPayload(
      offset = None,
      limit = None,
      timeout = None,
      allowedUpdates = None
    )
  )
}

/** Use this method to receive incoming updates using long polling
  * ([[https://en.wikipedia.org/wiki/Push_technology#Long_polling wiki]]). Returns an Array of
  * [[https://core.telegram.org/bots/api#update Update]] objects.
  *
  * Notes:
  *   - \1. This method will not work if an outgoing webhook is set up.
  *   - 2. In order to avoid getting duplicate updates, recalculate offset after each server response.
  */
case class GetUpdates(payload: GetUpdatesPayload) extends Command[Seq[Update]] {
  override val name: String = "getUpdates"
  override def parameters: ApiParameters = JsonBody(payload)

  /** Identifier of the first update to be returned. Must be greater by one than the highest among the identifiers of
    * previously received updates. By default, updates starting with the earliest unconfirmed update are returned. An
    * update is considered confirmed as soon as [[https://core.telegram.org/bots/api#getupdates getUpdates]] is called
    * with an offset higher than its update_id. The negative offset can be specified to retrieve updates starting from
    * -offset update from the end of the updates queue. All previous updates will forgotten.
    */
  def withOffset(offset: Long): GetUpdates = copy(payload = payload.copy(offset = Some(offset)))

  /** Limits the number of updates to be retrieved. Values between 1-100 are accepted. Defaults to 100.
    */
  def withLimit(limit: Long): GetUpdates = copy(payload = payload.copy(limit = Some(limit)))

  /** Timeout in seconds for long polling. Defaults to 0, i.e. usual short polling. Should be positive, short polling
    * should be used for testing purposes only.
    */
  def withTimeout(duration: Duration): GetUpdates = copy(payload = payload.copy(timeout = Some(duration.get(SECONDS))))

  /** A JSON-serialized list of the update types you want your bot to receive. For example, specify [“message”,
    * “edited_channel_post”, “callback_query”] to only receive updates of these types. See
    * [[https://core.telegram.org/bots/api#update Update]] for a complete list of available update types. Specify an
    * empty list to receive all update types except chat_member (default). If not specified, the previous setting will
    * be used.
    *
    * Please note that this parameter doesn't affect updates created before the call to the getUpdates, so unwanted
    * updates may be received for a short period of time.
    */
  def withAllowedUpdates(updateTypes: Set[UpdateType]): GetUpdates =
    copy(payload = payload.copy(allowedUpdates = Some(updateTypes)))
}
