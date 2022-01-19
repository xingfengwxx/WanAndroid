package com.wangxingxing.wanandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wangxingxing.network.observer.StateLiveData
import com.wangxingxing.wanandroid.bean.BannerBean
import com.wangxingxing.wanandroid.bean.HomeArticleBean
import com.wangxingxing.wanandroid.db.DBManager
import com.wangxingxing.wanandroid.db.entity.CollectEntity
import com.wangxingxing.wanandroid.net.HomePageRepository
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class HomeViewModel : ViewModel() {

    private val repository by lazy { HomePageRepository() }

    val bannerLiveData = StateLiveData<List<BannerBean>>()
    val bannerList: MutableList<BannerBean> = ArrayList()

    val topArticleLiveData = StateLiveData<List<HomeArticleBean>>()
    var topArticleList: MutableList<HomeArticleBean> = ArrayList()
    var topArticleCollectList = MutableLiveData<List<HomeArticleBean>>()

    val collectLiveData = StateLiveData<Void>()
    val unCollectLiveData = StateLiveData<Void>()

    fun getBannerInfo() {
        viewModelScope.launch {
            bannerLiveData.value = repository.getBannerInfo()
        }
    }

    fun getTopArticle() {
        viewModelScope.launch {
            topArticleLiveData.value = repository.getTopArticle()
        }
    }

    fun collectOrCancel(bean: HomeArticleBean) {
        viewModelScope.launch {
            val dao = DBManager.db.collectDao()
            val collectEntity = dao.findById(bean.id)

            if (collectEntity == null) {
                collectLiveData.value = repository.collectArticle(bean.id, bean.title, bean.link, bean.author)
                dao.insert(CollectEntity(0, bean.id))
            } else {
                unCollectLiveData.value = repository.unCollectArticle(bean.id)
                dao.delete(collectEntity)
            }
        }
    }

    fun queryCollectData(list: List<HomeArticleBean>) {
        viewModelScope.launch {
            topArticleCollectList.value = repository.getTopArticleCollect(list)
        }

    }


}