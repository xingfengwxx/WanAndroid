package com.wangxingxing.wanandroid

import com.alibaba.android.arouter.launcher.ARouter
import com.wangxingxing.wanandroid.bean.HomeArticleBean

/**
 * author : 王星星
 * date : 2022/1/21 10:38
 * email : 1099420259@qq.com
 * description :
 */
object ARouterIntent {

    /**
     * 跳转Web页面
     *
     * @param bean
     */
    fun toWebActivity(bean: HomeArticleBean) {
        ARouter.getInstance()
            .build(Constants.PATH_WEB)
            .withString(Constants.KEY_URL, bean.link)
            .withString(Constants.KEY_TITLE, bean.title)
            .navigation()
    }
}