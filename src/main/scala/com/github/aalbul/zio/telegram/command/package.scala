package com.github.aalbul.zio.telegram

import com.github.aalbul.zio.telegram.domain.JsonSerializationSupport

package object command extends JsonSerializationSupport {
  implicit class FileDescriptorOps(descriptor: FileDescriptor) {
    def asMultipart(name: String): MultipartBodyPart = descriptor match {
      case PathBasedFileDescriptor(path) => FilePart(name, path)
      case UrlFileDescriptor(url)        => StringPart(name, url)
      case IdFileDescriptor(url)         => StringPart(name, url)
    }
  }
}
