package com.eric.wanandroid.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.eric.wanandroid.base.mvp.BaseView
import com.eric.wanandroid.utils.LogUtils


/**
 * Created by eric on 20-9-21
 */
abstract class BaseFragment: Fragment(), BaseView {

    private var isInited = false
    private var toast: Toast ?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(setLayout(), container, false)
        initListener()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isInited){
            isInited = true
            initData()
        }
    }

    protected abstract fun setLayout(): Int

    open fun initListener(){
    }

    open fun initData(){
    }

    override fun showToast(msg: String){
        if (toast == null){
            toast = Toast.makeText(context, msg, Toast.LENGTH_LONG)
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