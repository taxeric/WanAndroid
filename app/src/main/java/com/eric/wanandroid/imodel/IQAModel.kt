package com.eric.wanandroid.imodel

import com.eric.wanandroid.base.mvp.BaseModel
import com.eric.wanandroid.base.net.ResponseResult
import com.eric.wanandroid.bean.QAEntity

/**
 * Created by eric on 20-10-10
 */
interface IQAModel: BaseModel {

    fun getQAList(result: ResponseResult<QAEntity>)
}