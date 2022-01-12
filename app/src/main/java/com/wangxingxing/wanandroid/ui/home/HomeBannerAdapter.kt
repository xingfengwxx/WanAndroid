package com.wangxingxing.wanandroid.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wangxingxing.wanandroid.bean.BannerBean
import com.wangxingxing.wanandroid.databinding.ItemBannerBinding
import com.youth.banner.adapter.BannerAdapter

/**
 * author : 王星星
 * date : 2022/1/12 15:14
 * email : 1099420259@qq.com
 * description :
 */
class HomeBannerAdapter(list: List<BannerBean>?) :
    BannerAdapter<BannerBean, HomeBannerAdapter.ViewHolder>(
        list
    ) {

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val binding = ItemBannerBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindView(holder: ViewHolder?, data: BannerBean?, position: Int, size: Int) {
        holder?.bind(data!!)
    }

    inner class ViewHolder(val binding: ItemBannerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bean : BannerBean) {
            binding.ivPic.load(bean.imagePath)
            binding.tvDesc.text = bean.title
        }
    }
}