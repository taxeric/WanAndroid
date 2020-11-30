package com.eric.wanandroid.presenter

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.eric.wanandroid.base.RvListener
import com.eric.wanandroid.base.mvp.BaseModel
import com.eric.wanandroid.base.mvp.BasePresenter
import com.eric.wanandroid.base.net.ResponseResult
import com.eric.wanandroid.bean.*
import com.eric.wanandroid.module.home.adapter.HomeBannerAdapter
import com.eric.wanandroid.module.home.adapter.HomeRvAdapter
import com.eric.wanandroid.imodelImpl.IHomeModelImpl
import com.eric.wanandroid.iview.IHomeView
import com.eric.wanandroid.ui.WebActivity
import com.eric.wanandroid.utils.LogUtils

/**
 * Created by eric on 20-9-22
 */
class HomePresenterImpl(
    private val view: IHomeView,
    private val context: Context,
    rvItemClickListener: RvListener.OnItemClickLoadMoreListener,
    bannerItemClickListener: HomeBannerAdapter.OnBannerItemClickListener
): BasePresenter(), HomeRvAdapter.SetFootViewText {

    private var articleAdapter: HomeRvAdapter
    private val articleMutableList = mutableListOf<HomeDataX>()

    private var bannerAdapter: HomeBannerAdapter
    private val bannerDataList = mutableListOf<HomeBannerData>()

    init {
        attachView(view)
        attachModel()
        LogUtils.i("is null ? ${view.getRv()} & ${view.getVP()}")
        view.getRv().layoutManager = LinearLayoutManager(context)

        articleAdapter = HomeRvAdapter(
            context,
            articleMutableList,
            this,
            view.getRv()
        )
        articleAdapter.setItemClickListener(rvItemClickListener)
        view.getRv().adapter = articleAdapter

        bannerAdapter = HomeBannerAdapter(
            context,
            bannerDataList
        )
        bannerAdapter.setOnBannerClickListener(bannerItemClickListener)
        view.getVP().adapter = bannerAdapter
    }

    override fun installModel(): BaseModel = IHomeModelImpl()

    fun getBanner(){
        (mModel as IHomeModelImpl).getBanner(object: ResponseResult<HomeBannerEntity>{
            override fun onSuccess(t: HomeBannerEntity) {
                bannerDataList.clear()
                bannerDataList.addAll(t.data)
                LogUtils.i("size = ${t.data.size}")
                bannerAdapter.notifyDataSetChanged()

                view.updateBanner(true)
            }

            override fun onFail(code: Int, msg: String) {
                super.onFail(code, msg)
                view.updateBanner(false)
            }
        })
    }

    fun getArticle(){
        (mModel as IHomeModelImpl).getArticle(object: ResponseResult<HomeArticleEntity>{
            override fun onSuccess(t: HomeArticleEntity) {
//                view.addArticleList(t.data.datas)
                articleMutableList.addAll(t.data.datas)
                articleAdapter.notifyDataSetChanged()
                view.updateArticle(true)
            }

            override fun onFail(code: Int, msg: String) {
                super.onFail(code, msg)
                view.updateArticle(false)
            }
        })
    }

    fun toWebView(position: Int){
        LogUtils.i(articleMutableList[position].link)
        WebActivity().intoActivity(context, "url", articleMutableList[position].link)
    }

    override fun loadComplete(): String = "点击加载更多"

    override fun loading(): String = "加载中..."
}