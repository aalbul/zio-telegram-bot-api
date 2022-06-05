package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.User.UserOps
import com.pengrad.telegrambot.model.ProximityAlertTriggered as LibProximityAlertTriggered

object ProximityAlertTriggered {
  implicit class ProximityAlertTriggeredOps(alert: LibProximityAlertTriggered) {
    def asScala: ProximityAlertTriggered = ProximityAlertTriggered(
      traveler = alert.traveler().asScala,
      watcher = alert.watcher().asScala,
      distance = alert.distance()
    )
  }
}

case class ProximityAlertTriggered(traveler: User, watcher: User, distance: Int)
