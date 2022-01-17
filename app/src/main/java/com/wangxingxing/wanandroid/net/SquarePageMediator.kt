package com.wangxingxing.wanandroid.net

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.blankj.utilcode.util.LogUtils
import com.wangxingxing.wanandroid.App
import com.wangxingxing.wanandroid.db.AppDatabase
import com.wangxingxing.wanandroid.db.entity.SquareArticleEntity
import com.wangxingxing.wanandroid.ext.isConnectedNetwork

/**
 * author : 王星星
 * date : 2022/1/14 17:05
 * email : 1099420259@qq.com
 * description :
 */
var count = 0
@OptIn(ExperimentalPagingApi::class)
class SquarePageMediator(
    private val api: ApiService,
    private val database: AppDatabase
) : RemoteMediator<Int, SquareArticleEntity>()  {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, SquareArticleEntity>
    ): MediatorResult {
        try {
            LogUtils.d("loadType: $loadType")
            //第一步，判断 LoadType
            val pageKey = when (loadType) {
                //首次访问 或者调用 PagingDataAdapter.refresh()
                LoadType.REFRESH -> null
                //在当前列表头部添加数据的时候使用
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                //上拉加载更多时触发
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
//                    lastItem.page
                    count++
                }
            }

            // 无网络，加载本地数据
            if (!App.instance.isConnectedNetwork()) {
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            //第二步，请求网络分页数据
            val pageNum = pageKey ?: 0
//            val pageNum = state.config.
            val result = api.getSquareArticleList(pageNum, state.config.pageSize)
            val endOfPaginationReached = result.data.datas.isEmpty()
            val items = result.data.datas.map { input ->
                SquareArticleEntity(
                    tid = input.id,
                    page = pageNum + 1,

                    id = input.id,
                    apkLink = input.apkLink,
                    audit = input.audit,
                    author = input.author,
                    canEdit = input.canEdit,
                    chapterId = input.chapterId,
                    chapterName = input.chapterName,
                    collect = input.collect,
                    courseId = input.courseId,
                    desc = input.desc,
                    descMd = input.descMd,
                    envelopePic = input.envelopePic,
                    fresh = input.fresh,
                    host = input.host,
                    link = input.link,
                    niceDate = input.niceDate,
                    niceShareDate = input.niceShareDate,
                    origin = input.origin,
                    prefix = input.prefix,
                    projectLink = input.projectLink,
                    publishTime = input.publishTime,
                    realSuperChapterId = input.realSuperChapterId,
                    selfVisible = input.selfVisible,
                    shareDate = input.shareDate,
                    shareUser = input.shareUser,
                    superChapterId = input.superChapterId,
                    superChapterName = input.superChapterName,
                    // TODO: 2022/1/14 tags = input.tags
//                    tags = listOf(),
                    title = input.title,
                    type = input.type,
                    userId = input.userId,
                    visible = input.visible,
                    zan = input.zan
                )
            }

            //第三步，插入数据库
            val squareArticleDao = database.squareArticleEntityDao()
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    //下拉刷新或者首次加载，清空
                    squareArticleDao.clearSquareArticle()
                }
                squareArticleDao.insertSquareArticle(items)
            }
            return MediatorResult.Success(endOfPaginationReached = items.isEmpty())
        } catch (e: Exception) {
            e.printStackTrace()
            return MediatorResult.Error(e)
        }
    }

}