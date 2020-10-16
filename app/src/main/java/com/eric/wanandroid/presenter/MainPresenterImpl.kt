package com.eric.wanandroid.presenter

import com.eric.wanandroid.base.mvp.BaseModel
import com.eric.wanandroid.base.mvp.BasePresenter
import com.eric.wanandroid.base.net.ResponseResult
import com.eric.wanandroid.bean.PersonalInfoEntity
import com.eric.wanandroid.cache.LocalCache
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
        attachModel()
    }

    override fun installModel(): BaseModel = IMainModelImpl()

    fun getInfo(){
        (mModel as IMainModelImpl).getPersonalInfo(object: ResponseResult<PersonalInfoEntity>{
            override fun onSuccess(t: PersonalInfoEntity) {
                LocalCache.setUserData(t.data)
                view.setHeaderImg()
                view.setHeaderName(LocalCache.getUserName())
                view.setHeaderRanking("现排名：${t.data.rank}\n总积分：${t.data.coinCount}")
                //把是否获取本地cookie的flag置为false，表示下次不需要从本地获取，直接从内存读取
                LocalCache.updateCookie = false
            }

            override fun onFail(code: Int, msg: String) {
                super.onFail(code, msg)
                view.setHeaderName("数据获取失败")
                view.setHeaderRanking("")
            }
        })
    }

    interface UpdateListener{
        fun update()
    }

    companion object{

        private var updateListener: UpdateListener ?= null

        fun registerUpdateListener(updateListener: UpdateListener){
            this.updateListener = updateListener
        }

        fun update(){
            updateListener!!.update()
        }
    }
}