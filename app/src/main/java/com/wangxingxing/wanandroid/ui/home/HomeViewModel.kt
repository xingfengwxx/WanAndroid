package com.wangxingxing.wanandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wangxingxing.network.observer.StateLiveData
import com.wangxingxing.wanandroid.bean.BannerBean
import com.wangxingxing.wanandroid.bean.HomeArticleBean
import com.wangxingxing.wanandroid.net.HomePageRepository
import kotlinx.coroutines.launch
import java.util.ArrayList

class HomeViewModel : ViewModel() {

    private val repository by lazy { HomePageRepository() }

    val bannerLiveData = StateLiveData<List<BannerBean>>()
    val bannerList: MutableList<BannerBean> = ArrayList()

    val topArticleLiveData = StateLiveData<List<HomeArticleBean>>()
    val topArticleList: MutableList<HomeArticleBean> = ArrayList()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

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

}