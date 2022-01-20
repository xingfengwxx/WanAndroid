package com.wangxingxing.wanandroid.ui.notifications

import com.blankj.utilcode.util.StringUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wangxingxing.wanandroid.R
import com.wangxingxing.wanandroid.bean.HomeArticleBean

/**
 * author : 王星星
 * date : 2022/1/20 15:12
 * email : 1099420259@qq.com
 * description :
 */
class WeChatArticleAdapter(layoutResId: Int) : BaseQuickAdapter<HomeArticleBean, BaseViewHolder>(
    layoutResId
) {
    override fun convert(holder: BaseViewHolder, item: HomeArticleBean) {
        holder.setGone(R.id.tv_top, true)
        holder.setVisible(R.id.tv_new, item.fresh)
        item.tags.forEach {
            holder.setVisible(R.id.tv_site_postings, it.name == StringUtils.getString(R.string.site_postings))
            holder.setVisible(R.id.tv_wechat_public_account, it.name == StringUtils.getString(R.string.wechat_public_account))
        }

        holder.setText(R.id.tv_author, item.author)
            .setText(R.id.tv_title, item.title)
            .setText(R.id.tv_time, item.niceDate)
            .setText(R.id.tv_type, "${item.chapterName}/${item.superChapterName}")

        holder.setImageResource(R.id.iv_fav, if (item.collect) R.drawable.ic_favorite else R.drawable.ic_favorite_border)
        
    }
}