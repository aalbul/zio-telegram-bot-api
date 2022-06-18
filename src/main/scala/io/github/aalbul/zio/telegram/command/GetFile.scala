package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.File

object GetFile {
  def of(fileId: String): GetFile = GetFile(fileId)
}

case class GetFile(fileId: String) extends Command[File] {
  override val name: String = "getFile"

  override def parameters: ApiParameters = MultipartBody.of(stringPart("file_id", fileId))
}
