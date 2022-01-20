package com.wangxingxing.wanandroid.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.StringUtils
import com.wangxingxing.wanandroid.R
import com.wangxingxing.wanandroid.bean.HomeArticleBean
import com.wangxingxing.wanandroid.databinding.ItemHomeArticleBinding

/**
 * author : 王星星
 * date : 2022/1/13 17:02
 * email : 1099420259@qq.com
 * description :
 */
class HomeArticleAdapter(val list: List<HomeArticleBean>, private val listener: IListener) :
    RecyclerView.Adapter<HomeArticleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemHomeArticleBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bean = list[position]
        holder.binding.tvTop.visibility = View.VISIBLE
        holder.binding.tvNew.isVisible = bean.fresh
        bean.tags.forEach {
            holder.binding.tvSitePostings.isVisible =
                it.name == StringUtils.getString(R.string.site_postings)

            holder.binding.tvWechatPublicAccount.isVisible =
                it.name == StringUtils.getString(R.string.wechat_public_account)
        }

        holder.binding.tvAuthor.text = bean.author
        holder.binding.tvTitle.text = bean.title
        holder.binding.tvTime.text = bean.niceDate
        holder.binding.tvType.text = "${bean.chapterName}/${bean.superChapterName}"

        holder.binding.ivFav.setImageResource(if (bean.collect) R.drawable.ic_favorite else R.drawable.ic_favorite_border)

        holder.binding.ivFav.setOnClickListener {
            listener.collectOrCancel(it, position)
        }

        holder.binding.root.setOnClickListener {
            listener.onItemClick(it, position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(val binding: ItemHomeArticleBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    interface IListener {
        fun collectOrCancel(view: View, position: Int)
        fun onItemClick(view: View, position: Int)
    }
}