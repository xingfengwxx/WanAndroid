package com.wangxingxing.wanandroid.net

import com.wangxingxing.network.base.BaseRepository
import com.wangxingxing.network.entity.ApiResponse
import com.wangxingxing.wanandroid.bean.BasePageBean
import com.wangxingxing.wanandroid.bean.HomeArticleBean
import com.wangxingxing.wanandroid.bean.WeChatAccountBean

/**
 * author : 王星星
 * date : 2022/1/20 12:12
 * email : 1099420259@qq.com
 * description :
 */
class NotificationsRepo : BaseRepository() {

    private val mService by lazy {
        RetrofitClient.service
    }

    suspend fun getWeChatAccountList(): ApiResponse<List<WeChatAccountBean>> {
        return executeHttp {
            mService.getWeChatAccountList()
        }
    }

    suspend fun getWeChatArticleList(accountId: Int, pageNum: Int): BasePageBean<HomeArticleBean> {
        return mService.getWeChatArticleList(accountId, pageNum)
    }

}