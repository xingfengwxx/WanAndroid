package com.wangxingxing.wanandroid.db.dao

import androidx.room.*
import com.wangxingxing.wanandroid.db.entity.CollectEntity

/**
 * author : 王星星
 * date : 2022/1/18 16:29
 * email : 1099420259@qq.com
 * description :
 */
@Dao
interface CollectDao {

    @Query("SELECT * FROM CollectEntity")
    suspend fun getAll(): List<CollectEntity>

    @Query("SELECT * FROM CollectEntity WHERE cid IN (:cids)")
    suspend fun loadAllByIds(cids: IntArray): List<CollectEntity>

    @Query("SELECT * FROM CollectEntity WHERE cid = :cid")
    suspend fun findById(cid: Int): CollectEntity?

    @Query("DELETE FROM CollectEntity")
    suspend fun clear()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<CollectEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(collect: CollectEntity)

    @Delete
    suspend fun delete(collectEntity: CollectEntity)
}