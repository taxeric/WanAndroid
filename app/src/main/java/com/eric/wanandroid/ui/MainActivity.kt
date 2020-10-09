package com.eric.wanandroid.ui

import android.view.MenuItem
import android.view.View
import androidx.fragment.app.FragmentManager
import com.eric.wanandroid.R
import com.eric.wanandroid.base.ui.BaseActivity
import com.eric.wanandroid.base.ui.BaseFragment
import com.eric.wanandroid.cache.LocalCache
import com.eric.wanandroid.home.HomeFragment
import com.eric.wanandroid.iview.IMainView
import com.eric.wanandroid.presenter.MainPresenterImpl
import com.eric.wanandroid.test.TestFragment
import com.eric.wanandroid.utils.ActivityUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header_layout.view.*

class MainActivity: BaseActivity(), IMainView, NavigationView.OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemSelectedListener {

    private val presenter = MainPresenterImpl(this)
    private lateinit var headerView: View

    private lateinit var curFragment: BaseFragment
    private val fragments = arrayOf(
            HomeFragment(), TestFragment()
    )

    override fun setLayout(): Int = R.layout.activity_main

    override fun initListener() {
        bottom_nav.setOnNavigationItemSelectedListener(this)
    }

    override fun initData() {
        curFragment = fragments[0]
        ActivityUtils.addFragmentToActivity(supportFragmentManager, fragments[0], "home", R.id.main_frame_layout)
        initDrawerData()
    }

    /**
     * 初始化侧滑栏数据
     */
    private fun initDrawerData(){
        headerView = nav_view.getHeaderView(0)
        if (LocalCache.isLogin){
            presenter.getInfo()
        } else {
            headerView.header_name.text = getString(R.string.please_login)
            headerView.header_ranking.text = ""
        }
    }

    override fun setHeaderImg() {
        headerView.header_img.setImageResource(R.drawable.aa)
    }

    override fun setHeaderName(name: String) {
        headerView.header_name.text = name
    }

    override fun setHeaderRanking(ranking: String) {
        headerView.header_ranking.text = ranking
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var index = 0
        var tag = ""
        when (item.itemId){
            R.id.menu_home -> {
                tag = "home"
            }
            R.id.menu_qa -> {
                index = 1
                tag = "qa"
            }
        }
        switchFragment(index, tag)
        return true
    }

    /**
     * 切换fragment
     */
    private fun switchFragment(
        index: Int,
        tag: String
    ){
        ActivityUtils.switchFragment(supportFragmentManager, curFragment, fragments[index], tag, R.id.main_frame_layout)
    }
}