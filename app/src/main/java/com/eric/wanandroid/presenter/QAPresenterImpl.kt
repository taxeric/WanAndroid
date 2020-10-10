package com.eric.wanandroid.presenter

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.eric.wanandroid.base.RvListener
import com.eric.wanandroid.base.mvp.BaseModel
import com.eric.wanandroid.base.mvp.BasePresenter
import com.eric.wanandroid.base.net.ResponseResult
import com.eric.wanandroid.bean.QADataX
import com.eric.wanandroid.bean.QAEntity
import com.eric.wanandroid.imodel.IQAModel
import com.eric.wanandroid.imodelImpl.IQAModelImpl
import com.eric.wanandroid.iview.IQAView
import com.eric.wanandroid.module.qa.adapter.QARvAdapter

/**
 * Created by eric on 20-9-22
 */
class QAPresenterImpl constructor(
        private val view: IQAView,
        context: Context,
        rvItemClickListener: RvListener.OnItemClickListener
): BasePresenter(), QARvAdapter.SetFootViewText {

    private val adapter: QARvAdapter
    private val qaList  = mutableListOf<QADataX>()

    init {
        attachView(view)
        attachModel()
        view.getRv().layoutManager = LinearLayoutManager(context)
        adapter =
            QARvAdapter(
                context,
                qaList,
                this
            )
        adapter.setItemClickListener(rvItemClickListener)
        view.getRv().adapter = adapter
    }

    override fun installModel(): BaseModel = IQAModelImpl()

    fun getQAList(){
        (mModel as IQAModel).getQAList(object: ResponseResult<QAEntity>{
            override fun onSuccess(t: QAEntity) {
                qaList.addAll(t.data.datas)
                adapter.notifyDataSetChanged()
                view.updateQAList(true)
            }

            override fun onFail(code: Int, msg: String) {
                super.onFail(code, msg)
                view.updateQAList(false)
            }
        })
    }

    override fun loadComplete(): String = "点击加载更多"

    override fun loading(): String = "加载中"
}