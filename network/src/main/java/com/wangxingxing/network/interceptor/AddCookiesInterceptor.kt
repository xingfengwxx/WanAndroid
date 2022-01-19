package com.wangxingxing.network.interceptor

import com.blankj.utilcode.util.SPUtils
import com.wangxingxing.network.Constants
import okhttp3.Interceptor
import okhttp3.Response

/**
 * author : 王星星
 * date : 2022/1/19 12:17
 * email : 1099420259@qq.com
 * description : 添加Cookie拦截器
 */
class AddCookiesInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val cookies = SPUtils.getInstance().getStringSet(Constants.SP_KEY_COOKIE)
        if (cookies.isNotEmpty()) {
            cookies.forEach {
                builder.addHeader("Cookie", it)
            }
        }
        return chain.proceed(builder.build())
    }
}