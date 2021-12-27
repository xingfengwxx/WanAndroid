package com.wangxingxing.network

import com.jeremyliao.liveeventbus.LiveEventBus

/**
 * author : 王星星
 * date : 2021/10/14 17:27
 * email : 1099420259@qq.com
 * description : 工具类
 */

const val SHOW_TOAST = "show_toast";

fun toast(msg: String) {
    LiveEventBus.get<String>(SHOW_TOAST).post(msg)
}