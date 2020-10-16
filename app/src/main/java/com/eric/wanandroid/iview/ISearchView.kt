package com.eric.wanandroid.iview

import com.eric.wanandroid.base.mvp.BaseView
import com.eric.wanandroid.base.ui.flowlayout.FlowLayout

/**
 * Created by eric on 20-10-16
 */
interface ISearchView: BaseView {

    fun getFlowLayout(): FlowLayout
    fun getSearchContent(): String
    fun setSearchContent(content: String)
    fun disProgress()
}