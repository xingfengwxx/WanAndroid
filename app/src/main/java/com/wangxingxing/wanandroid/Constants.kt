package com.wangxingxing.wanandroid

import com.alibaba.android.arouter.launcher.ARouter

/**
 * author : 王星星
 * date : 2022/1/10 14:39
 * email : 1099420259@qq.com
 * description :
 */
class Constants  {

    companion object {
        //ARouter
        const val PATH_SPLASH = "/activity/splash"
        const val PATH_MAIN = "/activity/main"
        const val PATH_LOGIN = "/activity/login"
        const val PATH_WEB = "/activity/web"

        const val PATH_WE_CHAT_ARTICLE = "/fragment/we_chat_article"

        const val KEY_URL = "key_url"
        const val KEY_TITLE = "key_title"

        //DataStore
        const val PROTO_USER = "user.pb"
        const val PRE_KEY_LAST_USER_NAME = "pre_key_last_user_name"
        const val PRE_KEY_LAST_USER_PASSWORD = "pre_key_last_user_password"
    }
}