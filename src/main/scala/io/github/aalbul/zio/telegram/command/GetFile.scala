package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.GetFile.GetFilePayload
import io.github.aalbul.zio.telegram.domain.File

object GetFile {

  object GetFilePayload {
    implicit val getFilePayloadJsonCodec: JsonValueCodec[GetFilePayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class GetFilePayload(fileId: String)

  /** Constructs minimal [[GetFile]] command
    * @param fileId
    *   File identifier to get information about
    * @return
    *   [[GetFile]] builder
    */
  def of(fileId: String): GetFile = GetFile(GetFilePayload(fileId))
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
case class GetFile(payload: GetFilePayload) extends Command[File] {
  override val name: String = "getFile"
  override def parameters: ApiParameters = JsonBody(payload)
}
