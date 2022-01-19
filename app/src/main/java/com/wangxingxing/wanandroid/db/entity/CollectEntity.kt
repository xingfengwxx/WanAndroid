package com.wangxingxing.wanandroid.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * author : 王星星
 * date : 2022/1/18 16:24
 * email : 1099420259@qq.com
 * description : 收藏表
 */
@Entity
class CollectEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val cid: Int
) {

}