package com.eric.wanandroid.base.ui

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.eric.wanandroid.base.mvp.BasePresenter
import com.eric.wanandroid.base.mvp.BaseView
import com.eric.wanandroid.utils.DialogUtils
import com.eric.wanandroid.utils.LogUtils

/**
 * Created by eric on 20-9-21
 */
abstract class BaseActivity: AppCompatActivity(), BaseView {

    protected var dialog: Dialog ?= null

    private var toast: Toast ?= null
    private var isInited = false

    protected var presenter: BasePresenter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())

        initListener()
    }

    override fun onStart() {
        super.onStart()
        if (!isInited){
            isInited = true
            presenter = installPresenter()
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

    /**
     * 安装P层
     */
    open fun installPresenter(): BasePresenter?{
        return null
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
        dialog = DialogUtils.showLoadingDialog(this, "请稍后")
        dialog!!.show()
    }

    override fun disLoadingDialog() {
        DialogUtils.disDialog(dialog)
    }

    open fun intoActivity(context: Context){
        context.startActivity(Intent(context, this::class.java))
    }

    open fun intoActivity(context: Context, key: String, value: String){
        context.startActivity(Intent(context, this::class.java).putExtra(key, value))
    }

    open fun toActivityForResult(
        context: Context,
        requestCode: Int
    ){
        startActivityForResult(Intent(this, context::class.java), requestCode)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachVM()
    }
}