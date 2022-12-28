package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.SetMyDefaultAdministratorRights.SetMyDefaultAdministratorRightsPayload
import io.github.aalbul.zio.telegram.domain.ChatAdministratorRights
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object SetMyDefaultAdministratorRights {
  object SetMyDefaultAdministratorRightsPayload {
    implicit val setMyDefaultAdministratorRightsJsonCodec: JsonValueCodec[SetMyDefaultAdministratorRightsPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class SetMyDefaultAdministratorRightsPayload(
    rights: Option[ChatAdministratorRights],
    forChannels: Option[Boolean]
  )

  /** Constructs minimal [[SetMyDefaultAdministratorRights]] command
    * @return
    *   [[SetMyDefaultAdministratorRights]] builder
    */
  def of: SetMyDefaultAdministratorRights = SetMyDefaultAdministratorRights(
    SetMyDefaultAdministratorRightsPayload(rights = None, forChannels = None)
  )
}

/** Use this method to change the default administrator rights requested by the bot when it's added as an administrator
  * to groups or channels. These rights will be suggested to users, but they are are free to modify the list before
  * adding the bot. Returns True on success.
  */
case class SetMyDefaultAdministratorRights(payload: SetMyDefaultAdministratorRightsPayload) extends Command[Boolean] {
  override val name: String = "setMyDefaultAdministratorRights"
  override def parameters: ApiParameters = JsonBody(payload)

  /** A JSON-serialized object describing new default administrator rights. If not specified, the default administrator
    * rights will be cleared.
    */
  def withRights(rights: ChatAdministratorRights): SetMyDefaultAdministratorRights = copy(
    payload.copy(rights = Some(rights))
  )

  /** Pass True to change the default administrator rights of the bot in channels. Otherwise, the default administrator
    * rights of the bot for groups and supergroups will be changed.
    */
  def withForChannels(forChannels: Boolean): SetMyDefaultAdministratorRights = copy(
    payload.copy(forChannels = Some(forChannels))
  )
}
