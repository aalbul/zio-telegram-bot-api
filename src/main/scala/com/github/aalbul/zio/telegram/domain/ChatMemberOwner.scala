package com.github.aalbul.zio.telegram.domain

case class ChatMemberOwner(user: User, isAnonymous: Boolean, customTitle: Option[String]) extends ChatMember
