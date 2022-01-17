package com.wangxingxing.wanandroid.ui.dashboard.adapter

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.wangxingxing.wanandroid.databinding.NetworkStateItemBinding

/**
 * author : 王星星
 * date : 2022/1/14 16:41
 * email : 1099420259@qq.com
 * description :
 */
class NetworkStateItemViewHolder(
    private val binding: NetworkStateItemBinding,
    val retryCallback: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(data: LoadState) {
        binding.apply {
            // 正在加载，显示进度条
            llLoading.isVisible = data is LoadState.Loading

            // 加载失败，显示并点击重试按钮
            retryButton.isVisible = data is LoadState.Error
            retryButton.setOnClickListener { retryCallback() }

            // 加载失败显示错误原因
            errorMsg.isVisible = !(data as? LoadState.Error)?.error?.message.isNullOrBlank()
            errorMsg.text = (data as? LoadState.Error)?.error?.message
        }
    }
}