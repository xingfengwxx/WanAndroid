package com.wangxingxing.wanandroid.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wangxingxing.network.observer.StateLiveData
import com.wangxingxing.wanandroid.bean.WeChatAccountBean
import com.wangxingxing.wanandroid.net.NotificationsRepo
import kotlinx.coroutines.launch

class NotificationsViewModel : ViewModel() {

    private val repo by lazy { NotificationsRepo() }

    val weChatAccountLiveData = StateLiveData<List<WeChatAccountBean>>()

    fun getWeChatAccountList() {
        viewModelScope.launch {
            weChatAccountLiveData.value = repo.getWeChatAccountList()
        }
    }
}