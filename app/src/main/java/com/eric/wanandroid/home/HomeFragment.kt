package com.eric.wanandroid.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.eric.wanandroid.R
import com.eric.wanandroid.base.RvListener
import com.eric.wanandroid.base.ui.BaseFragment
import com.eric.wanandroid.bean.HomeBannerData
import com.eric.wanandroid.bean.HomeDataX
import com.eric.wanandroid.bean.createNullBannerData
import com.eric.wanandroid.bean.createNullHomeArticleData
import com.eric.wanandroid.home.adapter.HomeRvAdapter
import com.eric.wanandroid.iview.IHomeView
import com.eric.wanandroid.presenter.HomePresenterImpl
import com.eric.wanandroid.utils.LogUtils
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by eric on 20-9-21
 */
class HomeFragment: BaseFragment(), IHomeView, RvListener.OnItemClickListener {

    private lateinit var presenter: HomePresenterImpl

    override fun setLayout(): Int = R.layout.fragment_home

    override fun initData() {
        this.presenter = HomePresenterImpl(this, home_rv, this, context!!)
        this.presenter.getBanner()
        this.presenter.getArticle()
    }

    override fun onItemClick(position: Int) = presenter.getArticle()

    override fun updateBanner() {
        LogUtils.i("update banner ok")
    }

    override fun updateArticle() {
        LogUtils.i("update article list ok")
    }
}