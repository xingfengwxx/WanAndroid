package com.wangxingxing.wanandroid;

import androidx.annotation.Nullable;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.wangxingxing.wanandroid.bean.BannerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * author : 王星星
 * date : 2022/1/12 16:00
 * email : 1099420259@qq.com
 * description :
 */
public class Test {
    private List<BannerBean> list = new ArrayList<>();

    private void test() {
        Logger.addLogAdapter(new AndroidLogAdapter(){
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                //也可以根据priority的VERBOSE、DEBUG、INFO、WARN、ERROR等不同级别
                //进行过滤，只在发布版本中保留ERROR的打印等
                return BuildConfig.DEBUG;   //只在DEBUG模式下打印log
            }
        });
    }
}
