package com.eric.wanandroid.base.mvp

import java.lang.ref.Reference
import java.lang.ref.WeakReference


/**
 * Created by eric on 20-9-22
 */
abstract class BasePresenter {

    private var mView: Reference<BaseView> ?= null
    protected lateinit var mModel: BaseModel

    abstract fun installModel(): BaseModel

    fun attachView(view: BaseView){
        mView = WeakReference<BaseView>(view)
    }

    fun attachModel(){
        mModel = installModel()
    }

    fun detachVM(){
        if (mView != null){
            this.mView!!.clear()
            this.mView = null
        }
        mModel.onDestory()
    }
}