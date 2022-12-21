package io.github.aalbul.zio.telegram.syntax

import io.github.aalbul.zio.telegram.serialization.DefaultJsonCodecs

object all extends CommandSyntax with FileSyntax with DefaultJsonCodecs
