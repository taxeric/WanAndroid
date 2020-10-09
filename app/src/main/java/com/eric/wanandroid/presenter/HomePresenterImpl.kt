package com.eric.wanandroid.presenter

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eric.wanandroid.base.RvListener
import com.eric.wanandroid.base.mvp.BasePresenter
import com.eric.wanandroid.base.net.ResponseResult
import com.eric.wanandroid.bean.*
import com.eric.wanandroid.home.adapter.HomeRvAdapter
import com.eric.wanandroid.imodelImpl.IHomeModelImpl
import com.eric.wanandroid.iview.IHomeView
import com.eric.wanandroid.utils.LogUtils
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * Created by eric on 20-9-22
 */
class HomePresenterImpl(
    private val view: IHomeView,
    home_rv: RecyclerView,
    listener: RvListener.OnItemClickListener,
    context: Context
): BasePresenter(), HomeRvAdapter.SetFootViewText {

    private var articleAdapter: HomeRvAdapter
    private val articleMutableList = mutableListOf<HomeDataX>()
    private val bannerDataList = mutableListOf<HomeBannerData>()

    init {
        attachView(view)
        home_rv.layoutManager = LinearLayoutManager(context)
        bannerDataList.add(createNullBannerData())
        articleMutableList.add(createNullHomeArticleData())
        articleAdapter = HomeRvAdapter(context, bannerDataList, articleMutableList,this)
        articleAdapter.setItemClickListener(listener)
        home_rv.adapter = articleAdapter
    }

    private val homeModel = IHomeModelImpl()

    fun getBanner(){
        homeModel.getBanner(object: ResponseResult<HomeBannerEntity>{
            override fun onSuccess(t: HomeBannerEntity) {
//                view.addBannerList(t.data)
                bannerDataList.clear()
                bannerDataList.addAll(t.data)
                LogUtils.i("size = ${t.data.size}")
                articleAdapter.notifyItemChanged(0)
                view.updateBanner()
            }
        })
    }

    fun getArticle(){
        homeModel.getArticle(object: ResponseResult<HomeArticleEntity>{
            override fun onSuccess(t: HomeArticleEntity) {
//                view.addArticleList(t.data.datas)
                articleMutableList.addAll(t.data.datas)
                articleAdapter.notifyDataSetChanged()
                view.updateArticle()
            }
        })
    }

    override fun loadComplete(): String = "点击加载更多"

    override fun loading(): String = "加载中..."
}