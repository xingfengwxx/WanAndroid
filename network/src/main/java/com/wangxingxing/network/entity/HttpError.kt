package com.wangxingxing.network.entity

import com.google.gson.JsonParseException
import com.wangxingxing.network.toast
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.util.concurrent.CancellationException

/**
 * author : 王星星
 * date : 2021/10/14 17:58
 * email : 1099420259@qq.com
 * description : 异常处理
 */

enum class HttpError(var code: Int, var errorMsg: String) {
    TOKEN_EXPIRE(3001, "token is expired"),
    PARAMS_ERROR(4003, "param is error")
    // ...... more
}

internal fun handlingApiExceptions(code: Int?, errorMsg: String?) = when (code) {
    HttpError.TOKEN_EXPIRE.code -> toast(HttpError.TOKEN_EXPIRE.errorMsg)
    HttpError.PARAMS_ERROR.code -> toast(HttpError.PARAMS_ERROR.errorMsg)
    else -> errorMsg?.let { toast(it) }
}

internal fun handlingExceptions(e: Throwable) = when (e) {
    is HttpException -> toast(e.message())

    is CancellationException -> { }

    is SocketTimeoutException -> { }

    is JsonParseException -> { }

    else -> { }

}