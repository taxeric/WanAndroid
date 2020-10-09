package com.eric.wanandroid.iview

import com.eric.wanandroid.base.mvp.BaseView
import com.eric.wanandroid.bean.HomeBannerData
import com.eric.wanandroid.bean.HomeDataX


/**
 * Created by eric on 20-9-22
 */
interface IHomeView: BaseView {
    fun updateBanner()
    fun updateArticle()
}