package com.eric.wanandroid.base.mvp

import java.lang.ref.Reference
import java.lang.ref.WeakReference


/**
 * Created by eric on 20-9-22
 */
abstract class BasePresenter {

    private var mView: Reference<BaseView> ?= null

    fun attachView(view: BaseView){
        mView = WeakReference<BaseView>(view)
    }

    fun detachView(){
        if (mView != null){
            mView!!.clear()
            mView = null
        }
    }
}