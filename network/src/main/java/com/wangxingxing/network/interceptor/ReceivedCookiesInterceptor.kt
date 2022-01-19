package com.wangxingxing.network.interceptor

import com.blankj.utilcode.util.SPUtils
import com.wangxingxing.network.Constants
import com.wangxingxing.network.LogUtils
import okhttp3.Interceptor
import okhttp3.Response


/**
 * author : 王星星
 * date : 2022/1/19 10:10
 * email : 1099420259@qq.com
 * description : 登录接口接收cookie保存到本地
 */
class ReceivedCookiesInterceptor : Interceptor {

    val TAG = "ReceivedCookiesInterceptor"

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        //这里获取请求返回的cookie
        if (originalResponse.headers("Set-Cookie").isNotEmpty()) {
            val cookies = HashSet<String>()
            originalResponse.headers("Set-Cookie").forEach {
                LogUtils.d(TAG, "拦截cookie：$it")
                cookies.add(it)
            }

            SPUtils.getInstance().put(Constants.SP_KEY_COOKIE, cookies)
        }
        return originalResponse
    }
}