package com.wangxingxing.wanandroid.ui.home

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.orhanobut.logger.Logger
import com.wangxingxing.wanandroid.ARouterIntent
import com.wangxingxing.wanandroid.Constants
import com.wangxingxing.wanandroid.base.BaseFragment
import com.wangxingxing.wanandroid.bean.BannerBean
import com.wangxingxing.wanandroid.databinding.FragmentHomeBinding
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.listener.OnBannerListener

/**
 * author : 王星星
 * date : 2022/1/12 18:21
 * email : 1099420259@qq.com
 * description :
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeArticleAdapter.IListener {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var mAdapter: HomeArticleAdapter

    private var mCurPosition: Int? = null

    override fun initView() {
        binding.banner.addBannerLifecycleObserver(this)
            .setAdapter(HomeBannerAdapter(viewModel.bannerList))
            .indicator = CircleIndicator(context)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        mAdapter = HomeArticleAdapter(viewModel.topArticleList, this)
        binding.recyclerView.adapter = mAdapter
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
              viewModel.queryCollectData(it)
            }
        }

        viewModel.topArticleCollectList.observe(this) {
            viewModel.topArticleList.clear()
            viewModel.topArticleList.addAll(it)
            mAdapter.notifyDataSetChanged()
        }

        viewModel.collectLiveData.observeState(this) {
            onEmpty {
                Logger.i("收藏成功")
                mAdapter.list[mCurPosition!!].collect = true
                mAdapter.notifyDataSetChanged()
            }
        }

        viewModel.unCollectLiveData.observeState(this) {
            onEmpty {
                Logger.i("取消收藏成功")
                mAdapter.list[mCurPosition!!].collect = false
                mAdapter.notifyDataSetChanged()
            }
        }

        binding.banner.setOnBannerListener { data, position ->
            ARouter.getInstance()
                .build(Constants.PATH_WEB)
                .withString(Constants.KEY_URL, (data as BannerBean).url)
                .withString(Constants.KEY_TITLE, (data as BannerBean).title)
                .navigation()
        }
    }

    override fun collectOrCancel(view: View, position: Int) {
        mCurPosition = position
        viewModel.collectOrCancel(mAdapter.list[position])
    }

    override fun onItemClick(view: View, position: Int) {
        val bean = mAdapter.list[position]
        ARouterIntent.toWebActivity(bean)
    }
}