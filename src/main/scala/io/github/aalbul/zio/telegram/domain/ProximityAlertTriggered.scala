package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
case class ProximityAlertTriggered(traveler: User, watcher: User, distance: Int)
