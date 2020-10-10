package com.eric.wanandroid.imodelImpl

import com.eric.wanandroid.base.Config
import com.eric.wanandroid.base.net.ResponseResult
import com.eric.wanandroid.base.net.RetrofitUtils
import com.eric.wanandroid.bean.SquareEntity
import com.eric.wanandroid.imodel.ISquareModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by eric on 20-9-22
 */
@Suppress("UNREACHABLE_CODE")
class ISquareModelImpl: ISquareModel {

    private var personalInfoCall: Call<SquareEntity> ?= null
    private var nowPage = 0

    override fun getSquareArticle(result: ResponseResult<SquareEntity>) {
        personalInfoCall = RetrofitUtils.getInstance().get().getSquareArticle(nowPage.toString())
        personalInfoCall!!.enqueue(object: Callback<SquareEntity>{
            override fun onResponse(call: Call<SquareEntity>, response: Response<SquareEntity>) {
                if (response.isSuccessful){
                    val p = response.body()
                    nowPage += 1
                    result.onSuccess(p!!)
                } else {
                    result.onFail(response.code(), "get square info is error")
                }
            }

            override fun onFailure(call: Call<SquareEntity>, t: Throwable) {
                result.onFail(Config.FAIL, t.message!!)
            }
        })
    }

    override fun onDestory() {
        RetrofitUtils.disCall(personalInfoCall)
    }
}