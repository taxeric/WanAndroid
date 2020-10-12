package com.eric.wanandroid.base.net

import com.eric.wanandroid.cache.LocalCache
import com.eric.wanandroid.utils.LogUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by eric on 20-10-12
 */
class AddCookieInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val cookie = LocalCache.getCookie()
        if (cookie != null) {
            if (cookie.isNotEmpty()) {
                for (i in cookie) {
                    builder.addHeader("Cookie", i)
                }
            }
        }
        return chain.proceed(builder.build())
    }
}