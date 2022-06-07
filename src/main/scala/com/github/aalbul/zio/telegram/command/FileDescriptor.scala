package com.github.aalbul.zio.telegram.command

import java.nio.file.Path

object FileDescriptor {
  def pathDescriptor(path: String): FileDescriptor = PathBasedFileDescriptor(Path.of(path))
  def urlDescriptor(url: String): FileDescriptor = UrlFileDescriptor(url)
  def idDescriptor(id: String): FileDescriptor = IdFileDescriptor(id)
}

sealed trait FileDescriptor
case class PathBasedFileDescriptor(path: Path) extends FileDescriptor
case class UrlFileDescriptor(url: String) extends FileDescriptor
case class IdFileDescriptor(id: String) extends FileDescriptor