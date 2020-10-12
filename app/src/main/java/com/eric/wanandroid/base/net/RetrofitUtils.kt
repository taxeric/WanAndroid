package com.eric.wanandroid.base.net

import com.eric.wanandroid.utils.LogUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Proxy

/**
 * Created by eric on 20-9-21
 */
class RetrofitUtils private constructor(
    private val config: RetrofitConfig
) {

    private var api: OpenApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(config.baseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()
        api = retrofit.create(OpenApi::class.java)
    }

    fun get(): OpenApi {
        return api
    }

    private fun getClient(): OkHttpClient{
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            if (config.logEnable()){
                LogUtils.i(message)
            }
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(AddCookieInterceptor())
        if (!config.grabEnable()){
            httpClient.proxy(Proxy.NO_PROXY)
        }
        return httpClient.build()
    }

    companion object{

        private var INSTANCE: RetrofitUtils?= null

        fun init(config: RetrofitConfig){
            if (INSTANCE == null){
                synchronized(RetrofitUtils::class) {
                    if (INSTANCE == null) {
                        INSTANCE = RetrofitUtils(config)
                    }
                }
            }
        }

        fun getInstance(): RetrofitUtils {
            return INSTANCE!!
        }

        fun createRequest(
            baseUrl: String,
            client: OkHttpClient
        ): OpenApi{
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(OpenApi::class.java)
        }

        fun <T> disCall(call: Call<T>?){
            if (call == null){
                return
            }
            if (call.isExecuted){
                call.cancel()
            }
        }
    }
}