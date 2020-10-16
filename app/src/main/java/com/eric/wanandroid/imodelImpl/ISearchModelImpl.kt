package com.eric.wanandroid.imodelImpl

import com.eric.wanandroid.base.Config
import com.eric.wanandroid.base.net.ResponseResult
import com.eric.wanandroid.base.net.RetrofitUtils
import com.eric.wanandroid.bean.HotSearchEntity
import com.eric.wanandroid.bean.SearchEntity
import com.eric.wanandroid.imodel.ISearchModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by eric on 20-10-16
 */
class ISearchModelImpl: ISearchModel {

    private var hotSearchCall: Call<HotSearchEntity> ?= null
    private var searchCall: Call<SearchEntity> ?= null

    override fun getHotSearch(result: ResponseResult<HotSearchEntity>) {
        hotSearchCall = RetrofitUtils.getInstance().get().getHotSearch()
        hotSearchCall!!.enqueue(object: Callback<HotSearchEntity>{
            override fun onFailure(call: Call<HotSearchEntity>, t: Throwable) {
                result.onFail(Config.FAIL, t.message!!)
            }

            override fun onResponse(
                call: Call<HotSearchEntity>,
                response: Response<HotSearchEntity>
            ) {
                if (response.isSuccessful){
                    val h = response.body()
                    result.onSuccess(h!!)
                } else {
                    result.onFail(response.code(), "get hot search is fail")
                }
            }
        })
    }

    override fun searchArticle(page: String, k: String, result: ResponseResult<SearchEntity>) {
        searchCall = RetrofitUtils.getInstance().get().searchArticle(page, k)
        searchCall!!.enqueue(object: Callback<SearchEntity>{
            override fun onFailure(call: Call<SearchEntity>, t: Throwable) {
                result.onFail(Config.FAIL, t.message!!)
            }

            override fun onResponse(
                call: Call<SearchEntity>,
                response: Response<SearchEntity>
            ) {
                if (response.isSuccessful){
                    val h = response.body()
                    result.onSuccess(h!!)
                } else {
                    result.onFail(response.code(), "get search result is fail")
                }
            }
        })
    }

    override fun onDestory() {
        RetrofitUtils.disCall(hotSearchCall)
        RetrofitUtils.disCall(searchCall)
    }
}