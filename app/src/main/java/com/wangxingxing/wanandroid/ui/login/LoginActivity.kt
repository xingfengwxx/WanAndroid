package com.wangxingxing.wanandroid.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.jeremyliao.liveeventbus.LiveEventBus
import com.wangxingxing.network.SHOW_TOAST
import com.wangxingxing.wanandroid.Constants
import com.wangxingxing.wanandroid.userDataStore
import com.wangxingxing.wanandroid.databinding.ActivityLoginBinding
import kotlinx.coroutines.flow.collectLatest

/**
 * author : 王星星
 * date : 2022/1/10 16:02
 * email : 1099420259@qq.com
 * description :
 */

@Route(path = Constants.PATH_LOGIN)
class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        initEvent()
        initObserver()
    }

    private fun initEvent() {
        binding.apply {
            login.setOnClickListener {
                viewModel.login(
                    binding.tilUsername?.editText?.text.toString(),
                    binding.tilPassword?.editText?.text.toString()
                )
            }

            query?.setOnClickListener {
                lifecycleScope.launchWhenCreated {
                    userDataStore.data.collectLatest {
                        LogUtils.i("查询用户信息：nickname=${it.username}, coinCount=${it.coinCount}")
                        LogUtils.i("collectIds: ${it.collectIdsList}")
                    }
                }
            }
        }
    }

    private fun initObserver() {
        viewModel.userLiveData.observeState(this) {
            onSuccess {
                LogUtils.i(it)
                ToastUtils.showShort("登录成功")

                lifecycleScope.launchWhenCreated {
                    it?.let {
                        userDataStore.updateData { preferences ->
                            preferences.toBuilder()
                                .setAdmin(it.admin)
                                .addAllChapterTops(it.chapterTops)
                                .setCoinCount(it.coinCount)
                                .setEmail(it.email)
                                .addAllCollectIds(it.collectIds)
                                .setIcon(it.icon)
                                .setId(it.id)
                                .setNickname(it.nickname)
                                .setPassword(it.password)
                                .setPublicName(it.publicName)
                                .setToken(it.token)
                                .setType(it.type)
                                .setUsername(it.username)
                                .build()
                        }

                    }
                }

                finish()
            }

            onComplete {
                LogUtils.d("登录请求完成")
            }

            onException {
                LogUtils.e(it)
            }
        }

        LiveEventBus.get<String>(SHOW_TOAST).observe(this,
            { t -> ToastUtils.showShort(t) })
    }

}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}