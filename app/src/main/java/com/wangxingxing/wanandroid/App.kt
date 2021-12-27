package com.wangxingxing.wanandroid

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils

/**
 * author : 王星星
 * date : 2021/12/27 17:50
 * email : 1099420259@qq.com
 * description :
 */
open class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Utils.init(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationLifecycleObserver())

    }

    private inner class ApplicationLifecycleObserver : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        private fun onAppForeground() {
            LogUtils.d("App切到前台")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        private fun onAppBackground() {
            LogUtils.d("App切到后台")
        }
    }
}


