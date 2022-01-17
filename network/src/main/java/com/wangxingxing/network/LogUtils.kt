package com.wangxingxing.network

import android.util.Log
import androidx.annotation.IntDef


/**
 * author : 王星星
 * date : 2022/1/17 15:07
 * email : 1099420259@qq.com
 * description : 日志工具类，解决日志超长不打印的问题
 */
object LogUtils {

    const val V = Log.VERBOSE
    const val D = Log.DEBUG
    const val I = Log.INFO
    const val W = Log.WARN
    const val E = Log.ERROR
    const val A = Log.ASSERT

    @IntDef(V, D, I, W, E, A)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class TYPE

    private const val MAX_LOG_LENGTH = 2000

    private const val SEGMENT_SIZE = 3 * 1024

    fun v(tag: String?, msg: String?) {
        log(V, tag, msg)
    }

    fun d(tag: String?, msg: String?) {
        log(D, tag, msg)
    }

    fun i(tag: String?, msg: String?) {
        log(I, tag, msg)
    }

    fun w(tag: String?, msg: String?) {
        log(W, tag, msg)
    }

    fun e(tag: String?, msg: String?) {
        log(E, tag, msg)
    }

    private fun log(type: Int, tag: String?, msg: String?) {
        var msg = msg
        if (tag == null || tag.isEmpty() || msg == null || msg.isEmpty()) return
        val length = msg.length.toLong()
        if (length <= SEGMENT_SIZE) { // 长度小于等于限制直接打印
            when (type) {
                V -> Log.v(tag, msg)
                D -> Log.d(tag, msg)
                I -> Log.i(tag, msg)
                W -> Log.w(tag, msg)
                E -> Log.e(tag, msg)
            }
        } else {
            while (msg!!.length > SEGMENT_SIZE) { // 循环分段打印日志
                val logContent = msg.substring(0, SEGMENT_SIZE)
                msg = msg.replace(logContent, "")
                when (type) {
                    V -> Log.v(tag, logContent)
                    D -> Log.d(tag, logContent)
                    I -> Log.i(tag, logContent)
                    W -> Log.w(tag, logContent)
                    E -> Log.e(tag, logContent)
                }
            }
            // 打印剩余日志
            when (type) {
                V -> Log.v(tag, msg)
                D -> Log.d(tag, msg)
                I -> Log.i(tag, msg)
                W -> Log.w(tag, msg)
                E -> Log.e(tag, msg)
            }
        }
    }

}