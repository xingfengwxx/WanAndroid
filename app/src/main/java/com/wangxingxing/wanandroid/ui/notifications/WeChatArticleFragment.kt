package com.wangxingxing.wanandroid.ui.notifications

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.orhanobut.logger.Logger
import com.wangxingxing.wanandroid.Constants
import com.wangxingxing.wanandroid.R
import com.wangxingxing.wanandroid.base.BaseFragment
import com.wangxingxing.wanandroid.bean.HomeArticleBean
import com.wangxingxing.wanandroid.databinding.FragmentWeChatArticleBinding

/**
 * author : 王星星
 * date : 2022/1/20 14:30
 * email : 1099420259@qq.com
 * description :
 */
@Route(path = Constants.PATH_WE_CHAT_ARTICLE)
class WeChatArticleFragment : BaseFragment<FragmentWeChatArticleBinding>() {

    private var accountId: Int? = null

    private val viewModel: NotificationsViewModel by viewModels()

    private lateinit var mAdapter: WeChatArticleAdapter

    override fun initView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        mAdapter = WeChatArticleAdapter(R.layout.item_home_article)
        binding.recyclerView.adapter = mAdapter
    }

    override fun initData() {
        accountId = arguments?.getInt("accountId")
        Logger.i("accountId=$accountId")
        viewModel.getWeChatArticleList(accountId!!, 1)
    }

    override fun initObserver() {
        viewModel.articleLiveData.observe(this) {
            mAdapter.setNewInstance(it.data.datas as MutableList<HomeArticleBean>)
        }
    }
}