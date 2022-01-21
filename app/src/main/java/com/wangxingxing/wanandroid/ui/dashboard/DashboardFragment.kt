package com.wangxingxing.wanandroid.ui.dashboard

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.wangxingxing.wanandroid.ARouterIntent
import com.wangxingxing.wanandroid.Constants
import com.wangxingxing.wanandroid.base.BaseFragment
import com.wangxingxing.wanandroid.databinding.FragmentDashboardBinding
import com.wangxingxing.wanandroid.ui.dashboard.adapter.FooterAdapter
import com.wangxingxing.wanandroid.ui.dashboard.adapter.SquareArticleAdapter
import kotlinx.coroutines.flow.collectLatest

class DashboardFragment : BaseFragment<FragmentDashboardBinding>(), SquareArticleAdapter.IListener {

    private val viewModel: DashboardViewModel by viewModels()
    private val mAdapter by lazy { SquareArticleAdapter(requireContext(), this) }

    override fun initView() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            recyclerView.adapter = mAdapter.withLoadStateFooter(FooterAdapter(mAdapter, requireContext()))
        }

    }

    override fun initData() {

        lifecycleScope.launchWhenCreated {
            mAdapter.loadStateFlow.collectLatest { state ->
                binding.swiperRefresh.isRefreshing = state.refresh is LoadState.Loading
            }
        }
    }

    override fun initObserver() {
        viewModel.squareArticleNetLiveData.observe(this, {
            mAdapter.submitData(lifecycle, it)
            binding.swiperRefresh.isEnabled = false
        })

    }

    override fun collectOrCancel(view: View, position: Int) {

    }

    override fun onItemClick(view: View, position: Int) {
        val bean = mAdapter.snapshot().items[position]
        ARouterIntent.toWebActivity(bean)
    }


}