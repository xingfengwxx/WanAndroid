package com.wangxingxing.wanandroid.weight


import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout

import com.chad.library.adapter.base.viewholder.BaseViewHolder

import com.chad.library.adapter.base.loadmore.BaseLoadMoreView
import com.wangxingxing.wanandroid.R
import org.jetbrains.annotations.NotNull


/**
 * author : 王星星
 * date : 2022/1/21 11:15
 * email : 1099420259@qq.com
 * description : 自定义加载布局
 */
class CustomLoadMoreView : BaseLoadMoreView() {

    override fun getLoadComplete(holder: BaseViewHolder): View {
        return holder.itemView.findViewById<LinearLayout>(R.id.load_more_load_complete_view)
    }

    override fun getLoadEndView(holder: BaseViewHolder): View {
        return holder.itemView.findViewById<LinearLayout>(R.id.load_more_load_end_view)
    }

    override fun getLoadFailView(holder: BaseViewHolder): View {
        return holder.itemView.findViewById<FrameLayout>(R.id.load_more_load_fail_view)
    }

    override fun getLoadingView(holder: BaseViewHolder): View {
        return holder.itemView.findViewById<LinearLayout>(R.id.load_more_loading_view)
    }

    @NotNull
    override fun getRootView(@NotNull viewGroup: ViewGroup): View {
        return LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.custom_load_more, viewGroup, false)
    }
}