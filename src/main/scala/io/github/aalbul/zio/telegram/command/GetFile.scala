package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.File

object GetFile {

  /** Constructs minimal [[GetFile]] command
    * @param fileId
    *   File identifier to get information about
    * @return
    *   [[GetFile]] builder
    */
  def of(fileId: String): GetFile = GetFile(fileId)
}

/** Use this method to get basic information about a file and prepare it for downloading. For the moment, bots can
  * download files of up to 20MB in size. On success, a [[File]] object is returned. The file can then be downloaded via
  * the link [[https://api.telegram.org/file/bot<token>/<file_path>]], where <file_path> is taken from the response. It
  * is guaranteed that the link will be valid for at least 1 hour. When the link expires, a new one can be requested by
  * calling [[GetFile]] again.
  *
  * Note: This function may not preserve the original file name and MIME type. You should save the file's MIME type and
  * name (if available) when the File object is received.
  */
case class GetFile(fileId: String) extends Command[File] {
  override val name: String = "getFile"

  override def parameters: ApiParameters = MultipartBody.of(stringPart("file_id", fileId))
}
