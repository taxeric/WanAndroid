package com.eric.wanandroid.presenter

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.eric.wanandroid.base.RvListener
import com.eric.wanandroid.base.mvp.BaseModel
import com.eric.wanandroid.base.mvp.BasePresenter
import com.eric.wanandroid.base.net.ResponseResult
import com.eric.wanandroid.bean.SquareDataX
import com.eric.wanandroid.bean.SquareEntity
import com.eric.wanandroid.imodel.ISquareModel
import com.eric.wanandroid.imodelImpl.ISquareModelImpl
import com.eric.wanandroid.iview.ISquareView
import com.eric.wanandroid.module.square.adapter.SquareRvAdapter
import com.eric.wanandroid.ui.WebActivity
import com.eric.wanandroid.utils.LogUtils

/**
 * Created by eric on 20-9-22
 */
class SquarePresenterImpl constructor(
        private val view: ISquareView,
        private val context: Context,
        rvItemClickListener: RvListener.OnItemClickLoadMoreListener
): BasePresenter(), SquareRvAdapter.SetFootViewText {

    private val adapter: SquareRvAdapter
    private val squareArticles  = mutableListOf<SquareDataX>()

    init {
        attachView(view)
        attachModel()
        view.getRv().layoutManager = LinearLayoutManager(context)
        adapter = SquareRvAdapter(
                context,
                squareArticles,
                this
            )
        adapter.setItemClickListener(rvItemClickListener)
        view.getRv().adapter = adapter
    }

    override fun installModel(): BaseModel = ISquareModelImpl()

    fun getSquareArticle(){
        (mModel as ISquareModel).getSquareArticle(object: ResponseResult<SquareEntity>{
            override fun onSuccess(t: SquareEntity) {
                squareArticles.addAll(t.data.datas)
                adapter.notifyDataSetChanged()
                view.updateSquareArticle(true)
            }

            override fun onFail(code: Int, msg: String) {
                super.onFail(code, msg)
                view.updateSquareArticle(false)
            }
        })
    }

    fun toWebView(position: Int){
        LogUtils.i(squareArticles[position].link)
        WebActivity().intoActivity(context, "url", squareArticles[position].link)
    }

    override fun loadComplete(): String = "点击加载更多"

    override fun loading(): String = "加载中"
}