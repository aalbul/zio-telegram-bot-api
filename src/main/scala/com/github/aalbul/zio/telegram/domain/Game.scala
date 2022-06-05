package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.Animation.AnimationOps
import com.github.aalbul.zio.telegram.domain.MessageEntity.MessageEntityOps
import com.github.aalbul.zio.telegram.domain.PhotoSize.PhotoSizeOps
import com.pengrad.telegrambot.model.Game as LibGame

object Game {
  implicit class GameOps(game: LibGame) {
    def asScala: Game = Game(
      title = game.title(),
      description = game.description(),
      photo = game.photo().map(_.asScala),
      text = Option(game.text()),
      textEntities = Option(game.textEntities()).map(_.map(_.asScala)),
      animation = Option(game.animation()).map(_.asScala)
    )
  }
}

case class Game(
  title: String,
  description: String,
  photo: Seq[PhotoSize],
  text: Option[String],
  textEntities: Option[Seq[MessageEntity]],
  animation: Option[Animation]
)
