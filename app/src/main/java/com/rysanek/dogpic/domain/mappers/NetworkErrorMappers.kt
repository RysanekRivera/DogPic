package com.rysanek.dogpic.domain.mappers

import com.rysanek.dogpic.domain.utils.getHttpStatusCodeMessage

fun formulateErrorMessage(code: Int? = null, message: String? = null) =
    if(code != null) "$code: ${getHttpStatusCodeMessage(code)}\n${message}"
    else message?.let { message } ?: "Unknown Error"

