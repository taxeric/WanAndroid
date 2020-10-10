package com.eric.wanandroid.module.home

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.eric.wanandroid.R
import com.eric.wanandroid.base.RvListener
import com.eric.wanandroid.base.mvp.BasePresenter
import com.eric.wanandroid.base.ui.BaseFragment
import com.eric.wanandroid.module.home.adapter.HomeBannerAdapter
import com.eric.wanandroid.iview.IHomeView
import com.eric.wanandroid.presenter.HomePresenterImpl
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by eric on 20-9-21
 */
class HomeFragment: BaseFragment(), IHomeView, RvListener.OnItemClickListener,
    HomeBannerAdapter.OnBannerItemClickListener {

    override fun setLayout(): Int = R.layout.fragment_home

    override fun installPresenter(): BasePresenter = HomePresenterImpl(this,context!!,this, this)

    override fun initData() {
        (presenter as HomePresenterImpl).getBanner()
        (presenter as HomePresenterImpl).getArticle()
    }

    override fun onItemClick(position: Int) = (presenter as HomePresenterImpl).getArticle()

    override fun onBannerItemClick(url: String) {
        i("url = $url")
    }

    override fun getVP(): ViewPager = home_vp

    override fun getRv(): RecyclerView = home_rv

    override fun updateBanner(isSuccessful: Boolean) {
        if (!isSuccessful){
            showToast("获取数据失败")
        }
    }

    override fun updateArticle(isSuccessful: Boolean) {
        if (!isSuccessful){
            showToast("获取数据失败")
        }
    }
}