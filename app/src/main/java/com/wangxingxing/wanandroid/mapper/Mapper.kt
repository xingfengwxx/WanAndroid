package com.wangxingxing.wanandroid.mapper

/**
 * author : 王星星
 * date : 2022/1/14 17:55
 * email : 1099420259@qq.com
 * description :
 */
interface Mapper<I, O> {
    fun map(input: I): O
}