package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Chat, Message, User}
import io.github.aalbul.zio.telegram.projection.{MessageProjector, UpdateProjection}
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

import java.time.Instant

object MessageProjection {
  object Forward {
    def of(message: Message): Option[Forward] = for {
      date <- message.forwardDate
    } yield Forward(
      from = message.forwardFrom,
      fromChat = message.forwardFromChat,
      messageId = message.forwardFromMessageId,
      signature = message.forwardSignature,
      senderName = message.forwardSenderName,
      isAutomaticForward = message.isAutomaticForward,
      date = date
    )
  }

  case class Forward(
    from: Option[User],
    fromChat: Option[Chat],
    messageId: Option[Long],
    signature: Option[String],
    senderName: Option[String],
    isAutomaticForward: Option[Boolean],
    date: Instant
  )

  object Data {
    def of(message: Message): Option[Data] = for {
      from <- message.from
    } yield Data(
      id = message.messageId,
      from = from,
      chat = message.chat,
      forward = Forward.of(message),
      replyToMessage = message.replyToMessage.flatMap(MessageProjector.all.project),
      date = message.date
    )
  }

  case class Data(
    id: Long,
    from: User,
    chat: Chat,
    forward: Option[Forward],
    replyToMessage: Option[MessageProjection],
    date: Instant
  )
}

trait MessageProjection extends UpdateProjection {
  val data: Data
}
