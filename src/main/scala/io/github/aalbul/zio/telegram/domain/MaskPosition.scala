package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object MaskPosition {

  /** Constructs minimal [[MaskPosition]]
    * @param point
    *   The part of the face relative to which the mask should be placed. One of “forehead”, “eyes”, “mouth”, or “chin”.
    * @param xShift
    *   Shift by X-axis measured in widths of the mask scaled to the face size, from left to right. For example,
    *   choosing -1.0 will place mask just to the left of the default mask position.
    * @param yShift
    *   Shift by Y-axis measured in heights of the mask scaled to the face size, from top to bottom. For example, 1.0
    *   will place the mask just below the default mask position.
    * @param scale
    *   Mask scaling coefficient. For example, 2.0 means double size.
    * @return
    *   [[MaskPosition]] builder
    */
  def of(point: MaskPointType, xShift: Double, yShift: Double, scale: Double): MaskPosition =
    MaskPosition(point, xShift, yShift, scale)

  implicit val maskPositionJsonCodec: JsonValueCodec[MaskPosition] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

/** This object describes the position on faces where a mask should be placed by default.
  */
case class MaskPosition(point: MaskPointType, xShift: Double, yShift: Double, scale: Double)
