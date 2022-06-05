package com.github.aalbul.zio.telegram.domain

import com.pengrad.telegrambot.model.MaskPosition as LibMaskPosition

object MaskPosition {
  implicit class MaskPositionOps(maskPosition: LibMaskPosition) {
    def asScala: MaskPosition = MaskPosition(
      point = maskPosition.point(),
      xShift = maskPosition.xShift(),
      yShift = maskPosition.yShift(),
      scale = maskPosition.scale()
    )
  }
}

case class MaskPosition(point: String, xShift: Float, yShift: Float, scale: Float)
