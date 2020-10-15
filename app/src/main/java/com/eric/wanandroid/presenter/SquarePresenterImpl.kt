package com.eric.wanandroid.presenter

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.eric.wanandroid.base.RvListener
import com.eric.wanandroid.base.mvp.BaseModel
import com.eric.wanandroid.base.mvp.BasePresenter
import com.eric.wanandroid.base.net.BaseEntity
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
        rvItemClickListener: RvListener.OnItemClickLoadMoreListener,
        rvCollectedArticle: RvListener.OnCollectedItemListener
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
        adapter.setCollectArticleListener(rvCollectedArticle)
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

    fun collected(position: Int){
        if (squareArticles[position].collect){
            uncollectArticle(squareArticles[position].id, position)
        } else {
            collectArticle(squareArticles[position].id, position)
        }
    }

    private fun collectArticle(id: Int, position: Int){
        (mModel as ISquareModelImpl).collectArticle(id, object: ResponseResult<BaseEntity>{
            override fun onSuccess(t: BaseEntity) {
                if (t.errorCode == 0){
                    squareArticles[position].collect = true
                    adapter.notifyItemChanged(position)
                    view.showToast("收藏成功")
                } else {
                    view.showToast("收藏失败：" + t.errorMsg)
                }
            }

            override fun onFail(code: Int, msg: String) {
                super.onFail(code, msg)
            }
        })
    }

    private fun uncollectArticle(id: Int, position: Int){
        (mModel as ISquareModelImpl).uncollectArticle(id, object: ResponseResult<BaseEntity>{
            override fun onSuccess(t: BaseEntity) {
                if (t.errorCode == 0){
                    squareArticles[position].collect = false
                    adapter.notifyItemChanged(position)
                    view.showToast("取消收藏成功")
                } else {
                    view.showToast("取消收藏失败：" + t.errorMsg)
                }
            }

            override fun onFail(code: Int, msg: String) {
                super.onFail(code, msg)
            }
        })
    }

    override fun loadComplete(): String = "点击加载更多"

    override fun loading(): String = "加载中"
}