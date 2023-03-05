package com.rysanek.dogpic.domain.utils

import java.util.Locale

object ExtensionUtils {

    fun String.capitalized() = replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }

}