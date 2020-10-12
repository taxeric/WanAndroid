package com.eric.wanandroid.iview

import com.eric.wanandroid.base.mvp.BaseView

/**
 * Created by eric on 20-10-12
 */
interface ILoginView: BaseView {

    fun getUnm(): String
    fun getPwd(): String

    fun showResult(msg: String)
}