package com.eric.wanandroid.imodel

import com.eric.wanandroid.base.mvp.BaseModel
import com.eric.wanandroid.base.net.ResponseResult
import com.eric.wanandroid.bean.HomeArticleEntity
import com.eric.wanandroid.bean.HomeBannerEntity


/**
 * Created by eric on 20-9-22
 */
interface IHomeModel: BaseModel {

    fun getBanner(result: ResponseResult<HomeBannerEntity>)
    fun getArticle(result: ResponseResult<HomeArticleEntity>)
}