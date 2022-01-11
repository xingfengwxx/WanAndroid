package com.wangxingxing.wanandroid

import android.animation.Animator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.wangxingxing.wanandroid.databinding.ActivitySplashBinding

/**
 * author : 王星星
 * date : 2022/1/10 15:31
 * email : 1099420259@qq.com
 * description : 
 */
@Route(path = Constants.PATH_SPLASH)
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.root.postDelayed(Runnable {
//
//        }, 2000)

        binding.lavSplash.addAnimatorListener(object: Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                ARouter.getInstance().build(Constants.PATH_MAIN).navigation()
                finish()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

        })

    }
}