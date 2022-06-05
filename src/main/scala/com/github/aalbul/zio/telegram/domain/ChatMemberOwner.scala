package com.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
case class ChatMemberOwner(user: User, isAnonymous: Boolean, customTitle: Option[String]) extends ChatMember
