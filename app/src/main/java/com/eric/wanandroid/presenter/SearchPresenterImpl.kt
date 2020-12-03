package com.eric.wanandroid.presenter

import android.content.Context
import android.text.TextUtils
import android.view.View
import com.eric.wanandroid.base.mvp.BaseModel
import com.eric.wanandroid.base.mvp.BasePresenter
import com.eric.wanandroid.base.net.ResponseResult
import com.eric.wanandroid.base.ui.flowlayout.FlowLayoutAdapter
import com.eric.wanandroid.base.ui.flowlayout.OnTagCheckListener
import com.eric.wanandroid.bean.HotSearchEntity
import com.eric.wanandroid.bean.HotSearchEntityData
import com.eric.wanandroid.bean.SearchDataX
import com.eric.wanandroid.bean.SearchEntity
import com.eric.wanandroid.cache.LocalCache
import com.eric.wanandroid.imodelImpl.ISearchModelImpl
import com.eric.wanandroid.iview.ISearchView
import com.eric.wanandroid.utils.LogUtils

/**
 * Created by eric on 20-10-16
 */
class SearchPresenterImpl constructor(
    private val view: ISearchView,
    context: Context,
    onTagCheckListener: OnTagCheckListener
): BasePresenter() {

    private var adapter: FlowLayoutAdapter
    private val hotSearchData = mutableListOf<HotSearchEntityData>()
    private val searchData = mutableListOf<SearchDataX>()
    private var nowPage = 0

    init {
        attachView(view)
        attachModel()
        adapter = FlowLayoutAdapter(context, hotSearchData)
        view.getFlowLayout().let {
            it.setTagCheckListener(onTagCheckListener)
            it.setAdapter(adapter)
        }
    }

    override fun installModel(): BaseModel = ISearchModelImpl()

    fun setFlowLayoutVisibility(show: Boolean){
        if (show){
            view.getFlowLayout().visibility = View.VISIBLE
        } else {
            view.getFlowLayout().visibility = View.GONE
        }
    }

    fun getHotSearchData(){
        LogUtils.i("Local cache list is empty? ${LocalCache.hotSearch.isEmpty()}")
        if (LocalCache.hotSearch.isEmpty()) {
            (mModel as ISearchModelImpl).getHotSearch(object : ResponseResult<HotSearchEntity> {
                override fun onSuccess(t: HotSearchEntity) {
                    if (t.errorCode == 0) {
                        hotSearchData.clear()
                        hotSearchData.addAll(t.data)
                        LocalCache.hotSearch.clear()
                        LocalCache.hotSearch.addAll(t.data)
                        adapter.notifyDataSetChanged()
                        view.disProgress()
                    } else {
                        view.showToast(t.errorMsg)
                    }
                }

                override fun onFail(code: Int, msg: String) {
                    super.onFail(code, msg)
                    view.showToast("获取搜索热词失败")
                }
            })
        } else {
            hotSearchData.addAll(LocalCache.hotSearch)
            adapter.notifyDataSetChanged()
            view.disProgress()
        }
    }

    fun search(position: Int){
        val name = hotSearchData[position].name
        view.setSearchContent(name)
        search(name, true)
    }

    fun search(k: String, clearPage: Boolean){
        if (TextUtils.isEmpty(k)){
            return
        }
        if (clearPage){
            nowPage = 0
        }
        (mModel as ISearchModelImpl).searchArticle(nowPage.toString(), k, object: ResponseResult<SearchEntity>{
            override fun onSuccess(t: SearchEntity) {
                if (t.errorCode == 0){
                    if (clearPage){
                        searchData.clear()
                    }
                    searchData.addAll(t.data.datas)
                    nowPage ++
                    setFlowLayoutVisibility(false)
                    if (searchData.isEmpty()){
                        view.showToast("没有找到内容～")
                        nowPage --
                    }
                } else {
                    view.showToast("搜索失败xxx")
                }
            }

            override fun onFail(code: Int, msg: String) {
                super.onFail(code, msg)
                view.showToast("搜索异常")
            }
        })
    }
}