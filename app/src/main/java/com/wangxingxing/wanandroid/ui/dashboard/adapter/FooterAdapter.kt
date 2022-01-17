package com.wangxingxing.wanandroid.ui.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.wangxingxing.wanandroid.databinding.NetworkStateItemBinding

/**
 * author : 王星星
 * date : 2022/1/14 15:53
 * email : 1099420259@qq.com
 * description :
 */
class FooterAdapter(
    val adapter: SquareArticleAdapter,
    val context: Context
) : LoadStateAdapter<NetworkStateItemViewHolder>() {

    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) {
        //水平居中
        val params = holder.itemView.layoutParams
        if (params is StaggeredGridLayoutManager.LayoutParams) {
            params.isFullSpan = true
        }
        holder.bindData(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NetworkStateItemViewHolder {
        val binding = NetworkStateItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return NetworkStateItemViewHolder(binding) {
            adapter.retry()
        }
    }

}