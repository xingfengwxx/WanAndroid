package com.wangxingxing.wanandroid.ui.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.load
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.StringUtils
import com.wangxingxing.wanandroid.Constants
import com.wangxingxing.wanandroid.R
import com.wangxingxing.wanandroid.base.BaseFragment
import com.wangxingxing.wanandroid.databinding.FragmentMyBinding
import com.wangxingxing.wanandroid.userDataStore
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

/**
 * author : 王星星
 * date : 2022/1/10 15:41
 * email : 1099420259@qq.com
 * description :
 */


class MyFragment : BaseFragment<FragmentMyBinding>() {


    private val viewModel: MyViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        return binding.root
    }

    override fun initView() {
//        binding.ivHead
//            .load("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.jj20.com%2Fup%2Fallimg%2F1111%2F0Q91Q50307%2F1PQ9150307-8.jpg&refer=http%3A%2F%2Fpic.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644465689&t=6103de4c9d3172f6a7a534d4f09578d9") {
//                transformations(CircleCropTransformation())
//            }

        binding.btnLogin.setOnClickListener {
            ARouter.getInstance().build(Constants.PATH_LOGIN).navigation()
        }

        binding.apply {
            ivHead.load(R.drawable.ic_baseline_android_100)

            btnLogin.setOnClickListener {
                if (btnLogin.text.equals(StringUtils.getString(R.string.login))) {
                    ARouter.getInstance().build(Constants.PATH_LOGIN).navigation()
                } else {
                    lifecycleScope.launchWhenCreated {
                        context?.userDataStore?.updateData {
                            it.toBuilder().clear().build()
                        }
                    }
                    clearInfo()
                }
            }
        }

        updateInfo()

    }

    override fun initData() {
    }

    override fun initObserver() {
    }

    private fun updateInfo() {
        lifecycleScope.launchWhenCreated {
            context?.userDataStore?.data?.collectLatest {

                if (it.nickname.isEmpty()) {
                    binding.btnLogin.text = StringUtils.getString(R.string.login)
                } else {
                    binding.tvNickname.text = it.nickname
                    binding.tvCoin.text = "积分：${it.coinCount}"
                    binding.tvId.text = "id: ${it.id}"

                    binding.btnLogin.text = StringUtils.getString(R.string.logout)
                }
            }
        }
    }

    private fun clearInfo() {
        binding.tvNickname.text = ""
        binding.tvCoin.text = ""
        binding.tvId.text = ""
    }

}