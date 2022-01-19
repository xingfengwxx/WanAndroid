package com.wangxingxing.wanandroid.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wangxingxing.wanandroid.db.entity.SquareArticleEntity

/**
 * author : 王星星
 * date : 2022/1/14 15:15
 * email : 1099420259@qq.com
 * description :
 */
@Dao
interface SquareArticleDao {

    @Query("DELETE FROM SquareArticleEntity")
    suspend fun clearSquareArticle()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSquareArticle(squareArticleEntity: List<SquareArticleEntity>)

    @Query("SELECT * FROM SquareArticleEntity")
    fun getSquareArticle(): PagingSource<Int, SquareArticleEntity>
}