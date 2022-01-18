package com.wangxingxing.wanandroid.net

import androidx.paging.*
import com.wangxingxing.network.base.BaseRepository
import com.wangxingxing.wanandroid.bean.HomeArticleBean
import com.wangxingxing.wanandroid.db.AppDatabase
import com.wangxingxing.wanandroid.db.entity.SquareArticleEntity
import com.wangxingxing.wanandroid.mapper.Mapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 * author : 王星星
 * date : 2022/1/17 10:18
 * email : 1099420259@qq.com
 * description :
 */
@OptIn(ExperimentalPagingApi::class)
class SquarePageRepository(
    private val database: AppDatabase,
    private val mapper2Bean: Mapper<SquareArticleEntity, HomeArticleBean>
) : BaseRepository() {

    private val PAGE_SIZE = 20

    private val mService by lazy {
        RetrofitClient.service
    }

    fun getSquareArticleList(): Flow<PagingData<HomeArticleBean>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = 1,
                initialLoadSize = PAGE_SIZE
            ),
            remoteMediator = SquarePageMediator(mService, database) // 请求网络数据，放入数据库
        ) {
            database.squareArticleEntityDao().getSquareArticle() // 从数据库拿到数据
        }.flow
            .flowOn(Dispatchers.IO)
            .map { pagingData ->
                pagingData.map { mapper2Bean.map(it) } // 对数据进行转换，给到UI显示
            }
    }

    fun getSquareArticleListNet(): Flow<PagingData<HomeArticleBean>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { SquarePagingSource(mService)}
        ).flow
            .flowOn(Dispatchers.IO)
    }
}