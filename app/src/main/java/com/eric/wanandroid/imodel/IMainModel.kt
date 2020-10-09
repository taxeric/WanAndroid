package com.eric.wanandroid.imodel

import com.eric.wanandroid.base.mvp.BaseModel
import com.eric.wanandroid.base.net.ResponseResult
import com.eric.wanandroid.bean.PersonalInfoEntity


/**
 * Created by eric on 20-9-22
 */
interface IMainModel: BaseModel {

    fun getPersonalInfo(result: ResponseResult<PersonalInfoEntity>)
}