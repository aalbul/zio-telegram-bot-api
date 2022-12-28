package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.GetMyDefaultAdministratorRights.GetMyDefaultAdministratorRightsPayload
import io.github.aalbul.zio.telegram.domain.ChatAdministratorRights

object GetMyDefaultAdministratorRights {
  object GetMyDefaultAdministratorRightsPayload {
    implicit val getMyDefaultAdministratorRightsPayloadJsonCodec
      : JsonValueCodec[GetMyDefaultAdministratorRightsPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class GetMyDefaultAdministratorRightsPayload(forChannels: Option[Boolean])

  /** Constructs minimal [[GetMyDefaultAdministratorRights]] command
    * @return
    *   [[GetMyDefaultAdministratorRights]] builder
    */
  def of: GetMyDefaultAdministratorRights = GetMyDefaultAdministratorRights(
    GetMyDefaultAdministratorRightsPayload(forChannels = None)
  )
}

/** Use this method to get the current default administrator rights of the bot. Returns
  * [[https://core.telegram.org/bots/api#chatadministratorrights ChatAdministratorRights]] on success.
  */
case class GetMyDefaultAdministratorRights(payload: GetMyDefaultAdministratorRightsPayload)
  extends Command[ChatAdministratorRights] {
  override val name: String = "getMyDefaultAdministratorRights"
  override def parameters: ApiParameters = JsonBody(payload)

  /** Pass True to get default administrator rights of the bot in channels. Otherwise, default administrator rights of
    * the bot for groups and supergroups will be returned.
    */
  def withForChannels(forChannels: Boolean): GetMyDefaultAdministratorRights = copy(
    payload.copy(forChannels = Some(forChannels))
  )
}
