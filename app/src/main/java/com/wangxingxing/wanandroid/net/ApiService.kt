package com.wangxingxing.wanandroid.net

import com.wangxingxing.network.entity.ApiResponse
import com.wangxingxing.wanandroid.bean.BannerBean
import com.wangxingxing.wanandroid.bean.UserInfoBean
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * author : 王星星
 * date : 2022/1/10 16:44
 * email : 1099420259@qq.com
 * description :
 */
interface ApiService {

    /**
     * 登录
     *
     * @param userName
     * @param passWord
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(@Field("username") userName: String, @Field("password") passWord: String): ApiResponse<UserInfoBean?>

    /**
     * 首页Banner
     *
     * @return
     */
    @GET("banner/json")
    suspend fun getBannerInfo(): ApiResponse<List<BannerBean>>

    companion object {
        const val BASE_URL = "https://wanandroid.com/"
    }
}