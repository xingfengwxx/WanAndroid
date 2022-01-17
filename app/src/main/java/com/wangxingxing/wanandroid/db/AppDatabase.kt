package com.wangxingxing.wanandroid.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wangxingxing.wanandroid.db.entity.SquareArticleEntity

/**
 * author : 王星星
 * date : 2022/1/14 15:14
 * email : 1099420259@qq.com
 * description :
 */
@Database(
    entities = [SquareArticleEntity::class],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun squareArticleEntityDao(): SquareArticleDao
}