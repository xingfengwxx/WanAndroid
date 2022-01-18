package com.wangxingxing.wanandroid.net

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wangxingxing.wanandroid.bean.HomeArticleBean
import kotlinx.coroutines.delay
import java.lang.Exception

/**
 * author : 王星星
 * date : 2022/1/18 11:43
 * email : 1099420259@qq.com
 * description : 网络数据源
 */
class SquarePagingSource(private val apiService: ApiService) : PagingSource<Int, HomeArticleBean>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeArticleBean> {
        return try {
            val page = params.key ?: 0
            val pageSize = params.loadSize
//            delay(2000)
            val repoResponse = apiService.getSquareArticleList(page, pageSize)
            val items = repoResponse.data.datas
            val prevKey = if (page > 0) page - 1 else null
            val nextKey = if (items.isNotEmpty()) page + 1 else null
            LoadResult.Page(items, prevKey, nextKey)
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, HomeArticleBean>): Int? = null
}