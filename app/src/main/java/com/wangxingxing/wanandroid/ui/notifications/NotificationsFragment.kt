package com.wangxingxing.wanandroid.ui.notifications

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.tabs.TabLayoutMediator
import com.wangxingxing.wanandroid.Constants
import com.wangxingxing.wanandroid.base.BaseFragment
import com.wangxingxing.wanandroid.bean.WeChatAccountBean
import com.wangxingxing.wanandroid.databinding.FragmentNotificationsBinding
import java.nio.channels.InterruptedByTimeoutException

/**
 * author : 王星星
 * date : 2022/1/20 14:59
 * email : 1099420259@qq.com
 * description : 公众号页面
 */
class NotificationsFragment : BaseFragment<FragmentNotificationsBinding>() {

    private val viewModel: NotificationsViewModel by viewModels()

    private lateinit var articleFragmentAdapter: ArticleFragmentAdapter

    override fun initView() {

    }

    override fun initData() {
        viewModel.getWeChatAccountList()
    }

    override fun initObserver() {
        viewModel.weChatAccountLiveData.observeState(this) {
            onSuccess {
                articleFragmentAdapter = ArticleFragmentAdapter(this@NotificationsFragment, it)
                binding.viewPager.adapter = articleFragmentAdapter

                TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                    tab.text = it[position].name
                }.attach()
            }
        }
    }

    class ArticleFragmentAdapter(fragment: Fragment, private val items: List<WeChatAccountBean>) :
        FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int = items.size

        override fun createFragment(position: Int): Fragment {
            return ARouter.getInstance()
                .build(Constants.PATH_WE_CHAT_ARTICLE)
                .withInt("accountId", items[position].id)
                .navigation() as Fragment
        }

    }

}