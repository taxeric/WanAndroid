package com.eric.wanandroid.base.net


/**
 * Created by eric on 20-9-21
 */
interface RetrofitConfig {

    fun baseUrl(): String
    fun grabEnable(): Boolean = true
    fun logEnable(): Boolean = true
}