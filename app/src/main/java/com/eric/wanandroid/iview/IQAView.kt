package com.eric.wanandroid.iview

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.eric.wanandroid.base.mvp.BaseView
import com.eric.wanandroid.bean.HomeBannerData
import com.eric.wanandroid.bean.HomeDataX


/**
 * Created by eric on 20-9-22
 */
interface IQAView: BaseView {
    fun getRv(): RecyclerView
    fun updateQAList(isSuccessful: Boolean)
}