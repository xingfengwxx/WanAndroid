package com.wangxingxing.wanandroid.ui.dashboard

import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wangxingxing.wanandroid.bean.HomeArticleBean
import com.wangxingxing.wanandroid.db.AppDatabase
import com.wangxingxing.wanandroid.db.DBManager
import com.wangxingxing.wanandroid.db.entity.SquareArticleEntity
import com.wangxingxing.wanandroid.mapper.Entity2BeanMapper
import com.wangxingxing.wanandroid.net.SquarePageRepository

class DashboardViewModel : ViewModel() {

    private val repository by lazy {
        SquarePageRepository(DBManager.db, Entity2BeanMapper())
    }

    val squareArticleLiveData = repository.getSquareArticleList()
        .cachedIn(viewModelScope).asLiveData()

}