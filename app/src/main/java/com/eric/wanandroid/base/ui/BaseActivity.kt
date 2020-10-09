package com.eric.wanandroid.base.ui

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eric.wanandroid.base.mvp.BaseView
import com.eric.wanandroid.utils.LogUtils

/**
 * Created by eric on 20-9-21
 */
abstract class BaseActivity: AppCompatActivity(), BaseView {

    protected var dialog: Dialog ?= null

    private var toast: Toast ?= null
    private var isInited = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())

        initListener()
    }

    override fun onStart() {
        super.onStart()
        if (!isInited){
            isInited = true
            initData()
        }
    }

    /**
     * 设置布局
     */
    protected abstract fun setLayout(): Int

    /**
     * 设置监听
     */
    open fun initListener(){
    }

    /**
     * 初始化数据
     */
    open fun initData(){
    }

    override fun showToast(msg: String){
        if (toast == null){
            toast = Toast.makeText(this, msg, Toast.LENGTH_LONG)
        } else {
            toast?.setText(msg)
        }
        toast?.show()
    }

    protected fun i(msg: String){
        LogUtils.i(msg)
    }

    protected fun e(msg: String){
        LogUtils.e(msg)
    }

    override fun showLoadingDialog() {
        TODO("Not yet implemented")
    }

    override fun disLoadingDialog() {
        TODO("Not yet implemented")
    }
}