package com.eric.wanandroid.ui

import android.app.Activity
import android.os.Environment
import android.view.MenuItem
import com.eric.wanandroid.R
import com.eric.wanandroid.base.mvp.BasePresenter
import com.eric.wanandroid.base.ui.BaseActivity
import com.eric.wanandroid.iview.ILoginView
import com.eric.wanandroid.presenter.LoginPresenterImpl
import com.eric.wanandroid.utils.LogUtils
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by eric on 20-10-12
 */
class LoginActivity: BaseActivity(), ILoginView {

    override fun setLayout(): Int = R.layout.activity_login

    override fun initListener() {
        setSupportActionBar(tool_bar)
        btn_login.setOnClickListener {
            (presenter as LoginPresenterImpl).login()
        }
    }

    override fun installPresenter(): BasePresenter = LoginPresenterImpl(this)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finishThis(false)
        }
        return true
    }

    private fun finishThis(request: Boolean){
        if (request){
            setResult(Activity.RESULT_OK)
        }
        finish()
    }

    override fun getUnm(): String = input_unm.text.toString()

    override fun getPwd(): String = input_pwd.text.toString()

    override fun showResult(msg: String) {
        if (msg.isEmpty()){
            finishThis(true)
        } else {
            showToast(msg)
        }
    }
}