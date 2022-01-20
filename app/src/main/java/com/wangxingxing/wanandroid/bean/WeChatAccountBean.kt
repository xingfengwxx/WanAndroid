package com.wangxingxing.wanandroid.bean

/**
 * author : 王星星
 * date : 2022/1/20 12:10
 * email : 1099420259@qq.com
 * description : 微信公众号tab
 */
data class WeChatAccountBean(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)