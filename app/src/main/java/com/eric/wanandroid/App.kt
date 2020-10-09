package com.eric.wanandroid

import android.app.Application
import com.eric.wanandroid.base.net.RetrofitConfig
import com.eric.wanandroid.base.net.RetrofitUtils
import com.eric.wanandroid.utils.ShareUtils

/**
 * Created by eric on 20-9-21
 */
class App: Application(), RetrofitConfig {

    override fun onCreate() {
        super.onCreate()
        RetrofitUtils.init(this)
        ShareUtils.init(this)
    }

    override fun baseUrl(): String = "https://www.wanandroid.com/"

    override fun grabEnable(): Boolean {
        return super.grabEnable()
    }

    override fun logEnable(): Boolean {
        return super.logEnable()
    }
}