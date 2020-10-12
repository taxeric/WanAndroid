package com.eric.wanandroid.presenter

import com.eric.wanandroid.base.mvp.BaseModel
import com.eric.wanandroid.base.mvp.BasePresenter
import com.eric.wanandroid.base.net.ResponseResult
import com.eric.wanandroid.bean.LoginResultEntity
import com.eric.wanandroid.cache.LocalCache
import com.eric.wanandroid.imodelImpl.ILoginModelImpl
import com.eric.wanandroid.iview.ILoginView

/**
 * Created by eric on 20-10-12
 */
class LoginPresenterImpl constructor(
    private val view: ILoginView
): BasePresenter() {

    init {
        attachView(view)
        attachModel()
    }

    override fun installModel(): BaseModel = ILoginModelImpl()

    fun login(){
        val unm = view.getUnm()
        val pwd = view.getPwd()
        if (unm.isEmpty() || pwd.isEmpty()){
            view.showResult("确保输入不为空")
            return
        }
        view.showLoadingDialog()
        (mModel as ILoginModelImpl).login(unm, pwd, object: ResponseResult<LoginResultEntity>{
            override fun onSuccess(t: LoginResultEntity) {
                if (t.errorCode == -1){
                    view.showResult(t.errorMsg)
                } else {
                    LocalCache.setLoginStatus(true)
                    LocalCache.setUserName(t.data.nickname)
                    view.showResult("")
                }
                view.disLoadingDialog()
            }

            override fun onFail(code: Int, msg: String) {
                super.onFail(code, msg)
                view.showResult("数据获取失败")
                view.disLoadingDialog()
            }
        })
    }
}