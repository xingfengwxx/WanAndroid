package com.wangxingxing.wanandroid.net

import com.wangxingxing.network.base.BaseRetrofitClient
import okhttp3.OkHttpClient

/**
 * author : 王星星
 * date : 2021/10/15 15:10
 * email : 1099420259@qq.com
 * description :
 */
object RetrofitClient : BaseRetrofitClient() {

    val service by lazy { getService(ApiService::class.java, ApiService.BASE_URL) }

    override fun handleBuilder(builder: OkHttpClient.Builder) = Unit
}