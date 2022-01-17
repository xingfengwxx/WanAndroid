package com.wangxingxing.wanandroid.db

import android.annotation.SuppressLint
import androidx.room.Room
import com.wangxingxing.wanandroid.App

/**
 * author : 王星星
 * date : 2022/1/17 11:08
 * email : 1099420259@qq.com
 * description :
 */
object DBManager {

    @SuppressLint("StaticFieldLeak")
    val db = Room.databaseBuilder(
        App.instance,
        AppDatabase::class.java,
        "WanAndroid.db"
    ).build()
}