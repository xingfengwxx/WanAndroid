package com.wangxingxing.wanandroid.net

import com.wangxingxing.network.base.BaseRepository
import com.wangxingxing.network.entity.ApiResponse
import com.wangxingxing.wanandroid.bean.UserInfoBean

/**
 * author : 王星星
 * date : 2022/1/10 16:52
 * email : 1099420259@qq.com
 * description : 用户信息仓库
 */
class UserRepository : BaseRepository() {
    private val mService by lazy {
        RetrofitClient.loginService
    }

    suspend fun login(username: String, password: String): ApiResponse<UserInfoBean?> {
        return executeHttp {
            mService.login(username, password)
        }
    }
}