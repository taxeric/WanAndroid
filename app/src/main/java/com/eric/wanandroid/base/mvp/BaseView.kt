package com.eric.wanandroid.base.mvp


/**
 * Created by eric on 20-9-22
 */
interface BaseView {

    fun showToast(msg: String)
    fun showLoadingDialog()
    fun disLoadingDialog()
}