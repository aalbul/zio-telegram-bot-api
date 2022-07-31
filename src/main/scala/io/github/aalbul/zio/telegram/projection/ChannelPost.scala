package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.domain.UpdateTypes.UpdateType
import io.github.aalbul.zio.telegram.domain.{Update, UpdateTypes}
import io.github.aalbul.zio.telegram.projection.message.MessageProjection

object ChannelPost {
  implicit val channelPostProjector: UpdateProjector[ChannelPost] = new UpdateProjector[ChannelPost] {
    override def project(update: Update): Option[ChannelPost] = for {
      channelPost <- update.channelPost
      message <- MessageProjector.all.project(channelPost)
    } yield ChannelPost(message)

    override val updateTypes: Set[UpdateType] = Set(UpdateTypes.ChannelPost)
  }
}

case class ChannelPost(message: MessageProjection) extends UpdateProjection
