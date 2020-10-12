package com.eric.wanandroid.base.net

import com.eric.wanandroid.base.Config
import com.eric.wanandroid.utils.ShareUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by eric on 20-10-12
 */
class SaveCookieInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val getCookies = response.headers("Set-Cookie")
        if (getCookies.isNotEmpty()){
            val cookies = HashSet<String>()
            for (i in getCookies){
                cookies.add(i)
            }
            ShareUtils.getInstance().put(Config.SP_KEY_COOKIE, cookies)
        }
        return response
    }
}