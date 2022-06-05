package com.github.aalbul.zio.telegram.domain

import com.pengrad.telegrambot.model.VideoChatEnded as LibVideoChatEnded

import java.time.Duration

object VideoChatEnded {
  implicit class VideoChatEndedOps(ended: LibVideoChatEnded) {
    def asScala: VideoChatEnded = VideoChatEnded(Duration.ofSeconds(ended.duration().toLong))
  }
}

case class VideoChatEnded(duration: Duration)
