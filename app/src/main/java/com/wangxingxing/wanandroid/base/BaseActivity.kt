package com.wangxingxing.wanandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.wangxingxing.wanandroid.databinding.ActivityBaseBinding
import com.wangxingxing.wanandroid.dialog.LoadingDialogFragment
import java.lang.reflect.ParameterizedType


/**
 * author : 王星星
 * date : 2022/1/12 10:06
 * email : 1099420259@qq.com
 * description :
 */
open abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    lateinit var binding: T
    private lateinit var baseBinding: ActivityBaseBinding

    private lateinit var loadingDialog: LoadingDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseBinding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(baseBinding.root)

        val cls =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
        binding = cls.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
            .invoke(null, LayoutInflater.from(this), baseBinding.root, true) as T

//        val cls = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
//        binding = cls.getMethod("inflate", LayoutInflater::class.java).invoke(null, LayoutInflater.from(this)) as T
//        setContentView(binding.root)

        loadingDialog = LoadingDialogFragment.newInstance()

        initView()
        initData()
        initObserver()
    }

    abstract fun initView()
    abstract fun initData()
    abstract fun initObserver()

    fun setToolbarTitle(title: CharSequence) {
        baseBinding.toolbar.title = title
    }

    fun setToolbarTitle(@StringRes resId: Int) {
        baseBinding.toolbar.setTitle(resId)
    }

    fun setToolbarTitleBack(@StringRes resId: Int) {
        setToolbarTitle(resId)
        showBack()
    }

    private fun showBack() {
        setSupportActionBar(baseBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    fun showLoading() {
        //避免接口请求返回时间太短，出现loading闪烁问题。500ms以内不会显示loading，显示loading后至少显示500ms。
//        baseBinding.loadingProgressBar.show()
        loadingDialog.show(supportFragmentManager, "loadingDialog")
    }

    fun dismissLoading() {
//        baseBinding.loadingProgressBar.hide()
        loadingDialog.dismiss()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


}