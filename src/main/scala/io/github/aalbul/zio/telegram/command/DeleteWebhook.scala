package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.DeleteWebhook.DeleteWebhookPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object DeleteWebhook {
  object DeleteWebhookPayload {
    implicit val deleteWebhookPayloadJsonCodec: JsonValueCodec[DeleteWebhookPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class DeleteWebhookPayload(dropPendingUpdates: Option[Boolean])

  /** Constructs minimal [[DeleteWebhook]] command
    * @return
    *   [[DeleteWebhook]] builder
    */
  def of: DeleteWebhook = DeleteWebhook(DeleteWebhookPayload(dropPendingUpdates = None))
}

case class DeleteWebhook(payload: DeleteWebhookPayload) extends Command[Boolean] {
  override val name: String = "deleteWebhook"
  override def parameters: ApiParameters = JsonBody(payload)

  /** Pass True to drop all pending updates
    */
  def withDropPendingUpdates(dropPendingUpdates: Boolean): DeleteWebhook =
    copy(payload.copy(dropPendingUpdates = Some(dropPendingUpdates)))
}
