package com.wangxingxing.wanandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * author : 王星星
 * date : 2022/1/11 18:27
 * email : 1099420259@qq.com
 * description :
 */
open abstract class BaseFragment<T : ViewBinding> : Fragment() {

    private var _binding: T? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val cls =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
        _binding = cls.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
            .invoke(null, LayoutInflater.from(requireActivity()), container, false) as T

        initView()
        initData()
        initObserver()

        return binding.root
    }

    abstract fun initView()
    abstract fun initData()
    abstract fun initObserver()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}