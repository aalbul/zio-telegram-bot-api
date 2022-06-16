package io.github.aalbul.zio.telegram.domain

import cats.syntax.functor.*
import io.circe.{Decoder, DecodingFailure, HCursor}

object ChatMember {
  private val decoders: Map[String, Decoder[ChatMember]] = Map(
    "creator" -> implicitly[Decoder[ChatMemberOwner]].widen,
    "administrator" -> implicitly[Decoder[ChatMemberAdministrator]].widen,
    "member" -> implicitly[Decoder[ChatMemberMember]].widen,
    "restricted" -> implicitly[Decoder[ChatMemberRestricted]].widen,
    "left" -> implicitly[Decoder[ChatMemberLeft]].widen,
    "kicked" -> implicitly[Decoder[ChatMemberBanned]].widen
  )

  implicit val chatMemberDecoder: Decoder[ChatMember] = (cursor: HCursor) =>
    for {
      status <- cursor.downField("status").as[String]
      decoder <- decoders
        .get(status)
        .toRight(DecodingFailure(s"No decoder for ChatMember with status `$status`", Nil))
      result <- decoder.apply(cursor)
    } yield result
}

trait ChatMember
