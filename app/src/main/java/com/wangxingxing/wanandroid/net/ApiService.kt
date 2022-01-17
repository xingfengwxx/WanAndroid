package com.wangxingxing.wanandroid.net

import com.wangxingxing.network.entity.ApiResponse
import com.wangxingxing.wanandroid.bean.BannerBean
import com.wangxingxing.wanandroid.bean.BasePageBean
import com.wangxingxing.wanandroid.bean.HomeArticleBean
import com.wangxingxing.wanandroid.bean.UserInfoBean
import retrofit2.http.*

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
    suspend fun login(
        @Field("username") userName: String,
        @Field("password") passWord: String
    ): ApiResponse<UserInfoBean?>

    /**
     * 首页Banner
     *
     * @return
     */
    @GET("banner/json")
    suspend fun getBannerInfo(): ApiResponse<List<BannerBean>>

    /**
     * 首页置顶文章
     *
     * @return
     */
    @GET("article/top/json")
    suspend fun getTopArticle(): ApiResponse<List<HomeArticleBean>>

    /**
     * 分页获取广场文章列表，从0开始
     *
     * @param pageNum 页码
     * @param pageSize 页大小
     */
    @GET("user_article/list/{pageNum}/json")
    suspend fun getSquareArticleList(
        @Path("pageNum") pageNum: Int,
        @Query("page_size") pageSize: Int
    ): BasePageBean<HomeArticleBean>


    companion object {
        const val BASE_URL = "https://wanandroid.com/"
    }
}