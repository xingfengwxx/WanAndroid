package com.wangxingxing.wanandroid.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.blankj.utilcode.util.SizeUtils
import com.wangxingxing.wanandroid.databinding.DialogFragmentLoadingBinding


/**
 * author : 王星星
 * date : 2022/1/18 14:43
 * email : 1099420259@qq.com
 * description :
 */
class LoadingDialogFragment : DialogFragment() {

    private lateinit var binding: DialogFragmentLoadingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogFragmentLoadingBinding.inflate(layoutInflater)
        binding.loadingProgressBar.show()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(SizeUtils.dp2px(100f), SizeUtils.dp2px(100f))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.loadingProgressBar.hide()
    }

    companion object {
        fun newInstance(): LoadingDialogFragment {
            return LoadingDialogFragment()
        }
    }
}