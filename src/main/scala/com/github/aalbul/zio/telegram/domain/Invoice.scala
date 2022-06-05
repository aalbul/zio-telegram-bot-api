package com.github.aalbul.zio.telegram.domain

import com.pengrad.telegrambot.model.Invoice as LibInvoice

object Invoice {
  implicit class InvoiceOps(invoice: LibInvoice) {
    def asScala: Invoice = Invoice(
      title = invoice.title(),
      description = invoice.description(),
      startParameter = invoice.startParameter(),
      currency = invoice.currency(),
      totalAmount = invoice.totalAmount()
    )
  }
}

case class Invoice(title: String, description: String, startParameter: String, currency: String, totalAmount: Int)
