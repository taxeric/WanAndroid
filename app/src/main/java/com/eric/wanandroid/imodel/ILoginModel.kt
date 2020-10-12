package com.eric.wanandroid.imodel

import com.eric.wanandroid.base.mvp.BaseModel
import com.eric.wanandroid.base.net.ResponseResult
import com.eric.wanandroid.bean.LoginResultEntity

/**
 * Created by eric on 20-10-12
 */
interface ILoginModel: BaseModel {

    fun login(unm: String, pwd: String, result: ResponseResult<LoginResultEntity>)
}