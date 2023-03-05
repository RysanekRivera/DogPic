package com.rysanek.dogpic.domain.utils

fun getHttpStatusCodeMessage(code: Int) = when(code) {
    in 400..499 -> getClientError(code)
    in 500..599 -> getServerError(code)
    else -> "Unknown Error"
}

private fun getClientError(code: Int) = when(code){
    400 -> "Bad Request"
    401 -> "Unauthorized"
    402 -> "Payment Required"
    403 -> "Forbidden"
    404 -> "Not Found"
    405 -> "Method not Allowed"
    406 -> "Not Acceptable"
    407 -> "Proxy Authentication Required"
    408 -> "Request Time Out"
    409 -> "Conflict"
    410 -> "Gone"
    411 -> "Length Required"
    412 -> "Precondition Failed"
    413 -> "Payload Too Large"
    414 -> "Uri Too Long"
    415 -> "Unsupported Media Type"
    416 -> "Range Not Satisfied"
    417 -> "Expectation Failed"
    418 -> "I'm A Tea Pot"
    421 -> "Misdirected Request"
    422 -> "Unprocessable Entity"
    423 -> "Locked"
    424 -> "Failed Dependency"
    426 -> "Upgrade Required"
    428 -> "PreconditionRequired"
    429 -> "Too Many Requests"
    431 -> "Request Header Fields Too Large"
    451 -> "UnavailableForLegalReasons"
    else -> "Client Error"
}

private fun getServerError(code: Int) = when(code) {
    500 -> "Internal Server Error"
    501 -> "Not Implemented"
    502 ->"BadGateway"
    503 -> "Service Unavailable"
    504 -> "Gateway Timeout"
    505 -> "Http Version Not Supported"
    506 -> "Variant Also Negates"
    507 -> "Insufficient Storage"
    508 -> "Loop Detected"
    510 -> "Not Extended"
    511 -> "Network Authentication Required"
    else -> "Server Error"
}
