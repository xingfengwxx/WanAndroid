package com.wangxingxing.wanandroid.net

import com.wangxingxing.network.entity.ApiResponse
import com.wangxingxing.wanandroid.bean.*
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

    /**
     * 收藏文章
     *
     * @param articleId 文章Id
     * @param title
     * @param link
     * @param author
     */
    @POST("lg/collect/user_article/update/{articleId}/json")
    suspend fun collectArticle(
        @Path("articleId") articleId: Int,
        @Query("title") title: String,
        @Query("link") link: String,
        @Query("author") author: String
    ): ApiResponse<Void>

    /**
     * 取消收藏文章
     *
     * @param articleId 文章Id
     */
    @POST("lg/uncollect_originId/{articleId}/json")
    suspend fun unCollectArticle(@Path("articleId") articleId: Int): ApiResponse<Void>

    /**
     * 公众号tab列表
     *
     * @return
     */
    @GET("wxarticle/chapters/json")
    suspend fun getWeChatAccountList(): ApiResponse<List<WeChatAccountBean>>


    companion object {
        const val BASE_URL = "https://wanandroid.com/"
    }
}