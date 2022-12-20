package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ProximityAlertTriggered {
  implicit val proximityAlertTriggeredJsonCodec: JsonValueCodec[ProximityAlertTriggered] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[ProximityAlertTriggered]]
    * @param traveler
    *   User that triggered the alert
    * @param watcher
    *   User that set the alert
    * @param distance
    *   The distance between the users
    * @return
    *   [[ProximityAlertTriggered]] builder
    */
  def of(traveler: User, watcher: User, distance: Long): ProximityAlertTriggered = ProximityAlertTriggered(
    traveler = traveler,
    watcher = watcher,
    distance = distance
  )
}

/** This object represents the content of a service message, sent whenever a user in the chat triggers a proximity alert
  * set by another user.
  */
case class ProximityAlertTriggered(traveler: User, watcher: User, distance: Long)
