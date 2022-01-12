package com.wangxingxing.wanandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.LogUtils
import com.wangxingxing.wanandroid.databinding.FragmentHomeBinding
import com.youth.banner.indicator.CircleIndicator

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        initView()
        initData()
        initObserver()

        return root
    }

    private fun initView() {
        binding.banner.addBannerLifecycleObserver(this)
            .setAdapter(HomeBannerAdapter(viewModel.bannerList))
            .indicator = CircleIndicator(context)
    }

    private fun initData() {
        viewModel.getBannerInfo()
    }

    private fun initObserver() {
        viewModel.bannerLiveData.observeState(this) {
            onSuccess {
                binding.banner.setDatas(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}