package com.github.aalbul.zio.telegram.domain.command

import java.nio.file.Path

sealed trait FileDescriptor
case class PathBasedFileDescriptor(path: Path) extends FileDescriptor
case class UrlFileDescriptor(url: String) extends FileDescriptor
case class IdFileDescriptor(id: String) extends FileDescriptor
