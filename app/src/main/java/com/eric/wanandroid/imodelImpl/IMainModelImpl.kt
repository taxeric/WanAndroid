package com.eric.wanandroid.imodelImpl

import com.eric.wanandroid.base.Config
import com.eric.wanandroid.base.net.ResponseResult
import com.eric.wanandroid.base.net.RetrofitUtils
import com.eric.wanandroid.bean.PersonalInfoEntity
import com.eric.wanandroid.imodel.IMainModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by eric on 20-9-22
 */
@Suppress("UNREACHABLE_CODE")
class IMainModelImpl: IMainModel {

    private var personalInfoCall: Call<PersonalInfoEntity> ?= null

    override fun getPersonalInfo(result: ResponseResult<PersonalInfoEntity>) {
        personalInfoCall = RetrofitUtils.getInstance().get().getPersonalInfo()
        personalInfoCall!!.enqueue(object: Callback<PersonalInfoEntity>{
            override fun onResponse(call: Call<PersonalInfoEntity>, response: Response<PersonalInfoEntity>) {
                if (response.isSuccessful){
                    val p = response.body()
                    result.onSuccess(p!!)
                } else {
                    result.onFail(response.code(), "get personal info is error")
                }
            }

            override fun onFailure(call: Call<PersonalInfoEntity>, t: Throwable) {
                result.onFail(Config.FAIL, t.message!!)
            }
        })
    }

    override fun onDestory() {
        RetrofitUtils.disCall(personalInfoCall)
    }
}