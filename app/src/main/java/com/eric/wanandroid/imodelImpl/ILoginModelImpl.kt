package com.eric.wanandroid.imodelImpl

import com.eric.wanandroid.base.Config
import com.eric.wanandroid.base.net.AddCookieInterceptor
import com.eric.wanandroid.base.net.ResponseResult
import com.eric.wanandroid.base.net.RetrofitUtils
import com.eric.wanandroid.base.net.SaveCookieInterceptor
import com.eric.wanandroid.bean.LoginResultEntity
import com.eric.wanandroid.imodel.ILoginModel
import com.eric.wanandroid.utils.LogUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.Proxy

/**
 * Created by eric on 20-10-12
 */
class ILoginModelImpl: ILoginModel {

    private var loginCall: Call<LoginResultEntity>?= null

    override fun login(unm: String, pwd: String, result: ResponseResult<LoginResultEntity>) {
        loginCall = RetrofitUtils
            .createRequest("https://www.wanandroid.com/", getClient(true, true))
            .login(unm, pwd)
        loginCall!!.enqueue(object: Callback<LoginResultEntity>{
            override fun onFailure(call: Call<LoginResultEntity>, t: Throwable) {
                result.onFail(Config.FAIL, t.message!!)
            }

            override fun onResponse(
                call: Call<LoginResultEntity>,
                response: Response<LoginResultEntity>
            ) {
                if (response.isSuccessful) {
                    val loginResult = response.body()
                    result.onSuccess(loginResult!!)
                } else {
                    result.onFail(response.code(), "get login result is error")
                }
            }
        })
    }

    private fun getClient(
        logEnable: Boolean,
        grabEnable: Boolean
    ): OkHttpClient{
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            if (logEnable){
                LogUtils.i(message)
            }
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(SaveCookieInterceptor())
        if (!grabEnable){
            httpClient.proxy(Proxy.NO_PROXY)
        }
        return httpClient.build()
    }

    override fun onDestory() {
        RetrofitUtils.disCall(loginCall)
    }
}