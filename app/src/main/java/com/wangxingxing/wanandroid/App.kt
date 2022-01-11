package com.wangxingxing.wanandroid

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Process
import android.util.Log
import androidx.datastore.dataStore
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.wangxingxing.wanandroid.proto.UserSerializer
import kotlin.system.exitProcess

/**
 * author : 王星星
 * date : 2021/12/27 17:50
 * email : 1099420259@qq.com
 * description :
 */
val Context.userDataStore by dataStore(
    fileName = Constants.PROTO_USER,
    serializer = UserSerializer
)

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

        initARouter()
        initLog()
        openCrashProtected()
    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {   // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()      // 打印日志
            ARouter.openDebug()    // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this) // 尽可能早，推荐在Application中初始化
    }

    private fun initLog() {
        LogUtils.getConfig().apply {
            globalTag = "wxx"
        }
    }

    private fun openCrashProtected() {
        LogUtils.d("openCrashProtected")
        Handler(Looper.getMainLooper()).post {
            while (true) {
                try {
                    Looper.loop()
                    LogUtils.d("main looper execute loop")
                } catch (e: Throwable) {
                    LogUtils.e("catch exception: $e")
                    e.printStackTrace()
                }
            }
        }

        Thread.setDefaultUncaughtExceptionHandler { _, e ->
            //捕获到异常，只打印日志，不杀进程
            LogUtils.e("${Thread.currentThread().name} 捕获到异常：$e")
            e.printStackTrace()

//            if (Looper.getMainLooper() == Looper.myLooper()) {
//                Process.killProcess(Process.myPid())
//                exitProcess(10)
//            }
        }
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


