package com.eric.wanandroid.imodel

import com.eric.wanandroid.base.mvp.BaseModel
import com.eric.wanandroid.base.net.ResponseResult
import com.eric.wanandroid.bean.HotSearchEntity
import com.eric.wanandroid.bean.SearchEntity

/**
 * Created by eric on 20-10-16
 */
interface ISearchModel: BaseModel {

    fun getHotSearch(result: ResponseResult<HotSearchEntity>)
    fun searchArticle(page: String, k: String, responseResult: ResponseResult<SearchEntity>)
}