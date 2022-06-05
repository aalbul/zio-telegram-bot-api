package com.github.aalbul.zio.telegram.domain

trait ApiResponse {
  val ok: Boolean
  val errorCode: Option[Int]
  val description: Option[String]
}
