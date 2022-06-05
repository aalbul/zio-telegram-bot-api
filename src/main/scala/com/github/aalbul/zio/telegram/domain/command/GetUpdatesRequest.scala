package com.github.aalbul.zio.telegram.domain.command

import io.circe.generic.extras.ConfiguredJsonCodec

import java.time.Duration

object GetUpdatesRequest {
  def empty: GetUpdatesRequest = GetUpdatesRequest(offset = None, limit = None, timeout = None, allowedUpdates = None)
}

@ConfiguredJsonCodec(encodeOnly = true)
case class GetUpdatesRequest(
  offset: Option[Long],
  limit: Option[Long],
  timeout: Option[Long],
  allowedUpdates: Option[Set[String]]
) {
  def withOffset(offset: Long): GetUpdatesRequest = copy(offset = Some(offset))
  def withLimit(limit: Long): GetUpdatesRequest = copy(limit = Some(limit))
  def withTimeout(duration: Duration): GetUpdatesRequest = copy(timeout = Some(duration.toSeconds))
  def withAllowedUpdates(updateTypes: Set[String]): GetUpdatesRequest = copy(allowedUpdates = Some(updateTypes))
}
