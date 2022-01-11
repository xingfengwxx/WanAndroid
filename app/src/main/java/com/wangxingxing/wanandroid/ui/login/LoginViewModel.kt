package com.wangxingxing.wanandroid.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wangxingxing.network.observer.StateLiveData
import com.wangxingxing.wanandroid.bean.UserInfoBean
import com.wangxingxing.wanandroid.net.UserRepository
import kotlinx.coroutines.launch

/**
 * author : 王星星
 * date : 2022/1/10 16:14
 * email : 1099420259@qq.com
 * description :
 */
class LoginViewModel : ViewModel() {

    private val repository by lazy { UserRepository() }

    val userLiveData = StateLiveData<UserInfoBean?>()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            userLiveData.value = repository.login(username, password)
        }
    }
}