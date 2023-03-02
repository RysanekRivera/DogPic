package com.rysanek.dogpic.domain.eventhandlers

interface HandleNetworkEvent {
    var success: () -> Unit
    var error: (String?) -> Unit
    var loading: () -> Unit
    var idle: () -> Unit

    fun setOnSuccess(onSuccess : () -> Unit)
    fun setOnError(onError : (String?) -> Unit)
    fun setOnLoading(onLoading : () -> Unit)
    fun setOnIdle(onIdle : () -> Unit)

    fun onSuccess()
    fun onError(message: String?)
    fun onLoading()
    fun onIdle()
}