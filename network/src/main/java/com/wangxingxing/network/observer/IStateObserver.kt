package com.wangxingxing.network.observer

import androidx.lifecycle.Observer
import com.wangxingxing.network.entity.*

/**
 * author : 王星星
 * date : 2021/10/14 18:58
 * email : 1099420259@qq.com
 * description : 状态管理
 */
abstract class IStateObserver<T> : Observer<ApiResponse<T>> {

    override fun onChanged(apiResponse: ApiResponse<T>?) {
        when (apiResponse) {
            is ApiSuccessResponse -> onSuccess(apiResponse.response)
            is ApiEmptyResponse -> onDataEmpty()
            is ApiFailedResponse -> onFailed(apiResponse.errorCode, apiResponse.errorMsg)
            is ApiErrorResponse -> onError(apiResponse.throwable)
        }
        onComplete()
    }

    abstract fun onSuccess(data: T)

    abstract fun onDataEmpty()

    abstract fun onError(e: Throwable)

    abstract fun onComplete()

    abstract fun onFailed(errorCode: Int?, errorMsg: String?)
}