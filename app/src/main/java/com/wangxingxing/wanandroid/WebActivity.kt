package com.wangxingxing.wanandroid

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ColorUtils
import com.orhanobut.logger.Logger
import com.wangxingxing.wanandroid.base.BaseActivity
import com.wangxingxing.wanandroid.databinding.ActivityWebBinding
import com.ycbjie.webviewlib.InterWebListener
import com.ycbjie.webviewlib.X5WebUtils

/**
 * author : 王星星
 * date : 2022/1/19 16:56
 * email : 1099420259@qq.com
 * description :
 */
@Route(path = Constants.PATH_WEB)
class WebActivity : BaseActivity<ActivityWebBinding>() {

    @JvmField
    @Autowired
    var url: String? = null

    @JvmField
    @Autowired
    var title: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbarTitleBack(title!!)
    }

    override fun initView() {
        binding.progress.show()
        binding.progress.setColor(
            ColorUtils.getColor(R.color.teal_200),
            ColorUtils.getColor(R.color.teal_700)
        )
    }

    override fun initData() {
        Logger.d("title: $title, url: $url")
        binding.webView.loadUrl(url)
    }

    override fun initObserver() {
        binding.webView.x5WebChromeClient.setWebListener(interWebListener)
        binding.webView.x5WebViewClient.setWebListener(interWebListener)
    }

    private val interWebListener = object : InterWebListener {
        override fun hindProgressBar() {
            binding.progress.hide()
        }

        override fun showErrorView(type: Int) {
            when (type) {
                X5WebUtils.ErrorMode.NO_NET -> {
                }
                X5WebUtils.ErrorMode.STATE_404 -> {
                }
                X5WebUtils.ErrorMode.RECEIVED_ERROR -> {
                }
                X5WebUtils.ErrorMode.SSL_ERROR -> {
                }
                else -> {
                }
            }
        }

        override fun startProgress(newProgress: Int) {
            binding.progress.setWebProgress(newProgress)
        }

        override fun showTitle(title: String?) {

        }

    }
}