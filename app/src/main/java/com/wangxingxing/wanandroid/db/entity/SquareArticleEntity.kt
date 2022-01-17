package com.wangxingxing.wanandroid.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * author : 王星星
 * date : 2022/1/14 15:17
 * email : 1099420259@qq.com
 * description : 广场文章Bean
 */
@Entity
data class SquareArticleEntity(
    @PrimaryKey
    val tid: Int,
    val page: Int = 0,

    val apkLink: String,
    val audit: Int,
    val author: String,
    val canEdit: Boolean,
    val chapterId: Int,
    val chapterName: String,
    val collect: Boolean,
    val courseId: Int,
    val desc: String,
    val descMd: String,
    val envelopePic: String,
    val fresh: Boolean,
    val host: String,
    val id: Int,
    val link: String,
    val niceDate: String,
    val niceShareDate: String,
    val origin: String,
    val prefix: String,
    val projectLink: String,
    val publishTime: Long,
    val realSuperChapterId: Int,
    val selfVisible: Int,
    val shareDate: Long,
    val shareUser: String,
    val superChapterId: Int,
    val superChapterName: String,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int
) {

}