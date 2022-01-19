package com.wangxingxing.wanandroid.net

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wangxingxing.network.base.BaseRepository
import com.wangxingxing.network.entity.ApiResponse
import com.wangxingxing.wanandroid.bean.BannerBean
import com.wangxingxing.wanandroid.bean.HomeArticleBean
import com.wangxingxing.wanandroid.db.DBManager

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

    suspend fun getTopArticle() : ApiResponse<List<HomeArticleBean>> {
        return executeHttp {
            mService.getTopArticle()
        }
    }

    suspend fun collectArticle(articleId: Int, title: String, link: String, author: String): ApiResponse<Void> {
        return executeHttp {
            mService.collectArticle(articleId, title, link, author)
        }
    }

    suspend fun unCollectArticle(articleId: Int): ApiResponse<Void> {
        return executeHttp {
            mService.unCollectArticle(articleId)
        }
    }

    suspend fun getTopArticleCollect(list: List<HomeArticleBean>) : List<HomeArticleBean> {
        val dao = DBManager.db.collectDao()
        list.forEach {
            val entity = dao.findById(it.id)
            it.collect = entity != null
        }
        return list
    }

}