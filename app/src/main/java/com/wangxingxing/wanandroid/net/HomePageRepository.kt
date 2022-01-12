package com.wangxingxing.wanandroid.net

import com.wangxingxing.network.base.BaseRepository
import com.wangxingxing.network.entity.ApiResponse
import com.wangxingxing.wanandroid.bean.BannerBean

/**
 * author : 王星星
 * date : 2022/1/12 14:31
 * email : 1099420259@qq.com
 * description :
 */
class HomePageRepository : BaseRepository() {
    private val mService by lazy {
        RetrofitClient.service
    }

    suspend fun getBannerInfo() : ApiResponse<List<BannerBean>> {
        return executeHttp {
            mService.getBannerInfo()
        }
    }
}