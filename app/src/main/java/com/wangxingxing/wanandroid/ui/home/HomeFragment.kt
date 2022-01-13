package com.wangxingxing.wanandroid.ui.home

import androidx.fragment.app.viewModels
import com.wangxingxing.wanandroid.base.BaseFragment
import com.wangxingxing.wanandroid.databinding.FragmentHomeBinding
import com.youth.banner.indicator.CircleIndicator

/**
 * author : 王星星
 * date : 2022/1/12 18:21
 * email : 1099420259@qq.com
 * description :
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    override fun initView() {
        binding.banner.addBannerLifecycleObserver(this)
            .setAdapter(HomeBannerAdapter(viewModel.bannerList))
            .indicator = CircleIndicator(context)
    }

    override fun initData() {
        viewModel.getBannerInfo()
        viewModel.getTopArticle()
    }

    override fun initObserver() {
        viewModel.bannerLiveData.observeState(this) {
            onSuccess {
                binding.banner.setDatas(it)
            }
        }

        viewModel.topArticleLiveData.observeState(this) {
            onSuccess {

            }
        }
    }

}