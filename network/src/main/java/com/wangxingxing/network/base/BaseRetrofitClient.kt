package com.wangxingxing.network.base

import com.wangxingxing.network.BuildConfig
import com.wangxingxing.network.LogUtils
import com.wangxingxing.network.interceptor.AddCookiesInterceptor
import com.wangxingxing.network.interceptor.ReceivedCookiesInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * author : 王星星
 * date : 2021/10/14 18:41
 * email : 1099420259@qq.com
 * description : RetrofitClient基类
 */
abstract class BaseRetrofitClient {

    private val TAG = "okHttp"

    companion object Client {
        private const val TIME_OUT = 5
    }

    private val client: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
            .addInterceptor(AddCookiesInterceptor())
            .addInterceptor(getHttpLoggingInterceptor())
            .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        handleBuilder(builder)
        builder.build()
    }

    private val loginClient: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
            .addInterceptor(ReceivedCookiesInterceptor())
            .addInterceptor(getHttpLoggingInterceptor())
            .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        handleBuilder(builder)
        builder.build()
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            LogUtils.w(TAG, it)
        })
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.BASIC
        }
        return logging
    }

    abstract fun handleBuilder(builder: OkHttpClient.Builder)

    open fun <Service> getService(serviceClass: Class<Service>, baseUrl: String): Service {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(serviceClass)
    }

    open fun <Service> getLoginService(serviceClass: Class<Service>, baseUrl: String): Service {
        return Retrofit.Builder()
            .client(loginClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(serviceClass)
    }
}