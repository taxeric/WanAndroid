package com.eric.wanandroid.presenter

import com.eric.wanandroid.base.mvp.BasePresenter
import com.eric.wanandroid.base.net.ResponseResult
import com.eric.wanandroid.bean.PersonalInfoEntity
import com.eric.wanandroid.imodelImpl.IMainModelImpl
import com.eric.wanandroid.iview.IMainView


/**
 * Created by eric on 20-9-22
 */
class MainPresenterImpl constructor(
        private val view: IMainView
): BasePresenter() {

    init {
        attachView(view)
    }

    private val mainModel = IMainModelImpl()

    fun getInfo(){
        mainModel.getPersonalInfo(object: ResponseResult<PersonalInfoEntity>{
            override fun onSuccess(t: PersonalInfoEntity) {
                view.setHeaderImg()
                view.setHeaderName(t.data.username)
                view.setHeaderRanking("现排名：${t.data.rank}\n总积分：${t.data.coinCount}")
            }

            override fun onFail(code: Int, msg: String) {
                super.onFail(code, msg)
                view.setHeaderName("数据获取失败")
                view.setHeaderRanking("")
            }
        })
    }
}