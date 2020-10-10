package com.eric.wanandroid.imodelImpl

import com.eric.wanandroid.base.Config
import com.eric.wanandroid.base.net.ResponseResult
import com.eric.wanandroid.base.net.RetrofitUtils
import com.eric.wanandroid.bean.PersonalInfoEntity
import com.eric.wanandroid.bean.QAEntity
import com.eric.wanandroid.imodel.IMainModel
import com.eric.wanandroid.imodel.IQAModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by eric on 20-9-22
 */
@Suppress("UNREACHABLE_CODE")
class IQAModelImpl: IQAModel {

    private var personalInfoCall: Call<QAEntity> ?= null
    private var nowPage = 1

    override fun getQAList(result: ResponseResult<QAEntity>) {
        personalInfoCall = RetrofitUtils.getInstance().get().getQAList(nowPage.toString())
        personalInfoCall!!.enqueue(object: Callback<QAEntity>{
            override fun onResponse(call: Call<QAEntity>, response: Response<QAEntity>) {
                if (response.isSuccessful){
                    val p = response.body()
                    nowPage += 1
                    result.onSuccess(p!!)
                } else {
                    result.onFail(response.code(), "get question & answer info is error")
                }
            }

            override fun onFailure(call: Call<QAEntity>, t: Throwable) {
                result.onFail(Config.FAIL, t.message!!)
            }
        })
    }

    override fun onDestory() {
        RetrofitUtils.disCall(personalInfoCall)
    }
}