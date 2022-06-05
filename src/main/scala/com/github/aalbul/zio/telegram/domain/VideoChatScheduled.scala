package com.github.aalbul.zio.telegram.domain

import com.pengrad.telegrambot.model.VideoChatScheduled as LibVideoChatScheduled

import java.time.Instant

object VideoChatScheduled {
  implicit class VideoChatScheduledOps(scheduled: LibVideoChatScheduled) {
    def asScala: VideoChatScheduled = VideoChatScheduled(startDate = Instant.ofEpochSecond(scheduled.startDate().toLong))
  }
}

case class VideoChatScheduled(startDate: Instant)
