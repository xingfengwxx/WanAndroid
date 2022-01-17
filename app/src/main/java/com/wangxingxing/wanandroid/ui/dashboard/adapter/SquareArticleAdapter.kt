package com.wangxingxing.wanandroid.ui.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.wangxingxing.wanandroid.R
import com.wangxingxing.wanandroid.bean.HomeArticleBean
import com.wangxingxing.wanandroid.databinding.ItemHomeArticleBinding

/**
 * author : 王星星
 * date : 2022/1/13 17:02
 * email : 1099420259@qq.com
 * description :
 */
class SquareArticleAdapter(private val context: Context) : PagingDataAdapter<HomeArticleBean, BindingViewHolder>(object : DiffUtil.ItemCallback<HomeArticleBean>(){
    override fun areItemsTheSame(oldItem: HomeArticleBean, newItem: HomeArticleBean): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: HomeArticleBean,
        newItem: HomeArticleBean
    ): Boolean {
        return oldItem == newItem
    }

}) {

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        getItem(position)?.let { bean ->
            val binding = holder.binding as ItemHomeArticleBinding

            binding.tvTop.visibility = View.VISIBLE
            holder.binding.tvNew.isVisible = bean.fresh
//            bean.tags.forEach {
//                holder.binding.tvSitePostings.isVisible =
//                    it.name == StringUtils.getString(R.string.site_postings)
//
//                holder.binding.tvWechatPublicAccount.isVisible =
//                    it.name == StringUtils.getString(R.string.wechat_public_account)
//            }

            binding.tvAuthor.text = bean.author
            binding.tvTitle.text = bean.title
            binding.tvTime.text = bean.niceDate
            binding.tvType.text = "${bean.chapterName}/${bean.superChapterName}"

            binding.ivFav.setImageResource(if (bean.collect) R.drawable.ic_favorite else R.drawable.ic_favorite_border)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = ItemHomeArticleBinding.inflate(LayoutInflater.from(context), parent, false)
        return BindingViewHolder(binding)
    }

}