package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Message, WebAppData}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object WebAppDataMessage {
  implicit val webAppDataProjector: MessageProjector[WebAppDataMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      webAppData <- message.webAppData
    } yield WebAppDataMessage(
      data = data,
      webAppData = webAppData
    )
}

case class WebAppDataMessage(data: Data, webAppData: WebAppData) extends ServiceMessageProjection
