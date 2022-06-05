package com.github.aalbul.zio.telegram.domain

case class ChatMemberBanned(user: User, untilDate: Int) extends ChatMember
