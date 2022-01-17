package com.wangxingxing.wanandroid.bean

/**
 * author : 王星星
 * date : 2022/1/17 11:59
 * email : 1099420259@qq.com
 * description : 分页数据封装
 */
data class BasePageBean<T>(
    val `data`: Data<T>,
    val errorCode: Int,
    val errorMsg: String,
) {
    data class Data<T>(
        val curPage: Int,
        val datas: List<T>,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int
    )
}