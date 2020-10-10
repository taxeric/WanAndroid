package com.eric.wanandroid.ui

import android.view.MenuItem
import android.view.View
import com.eric.wanandroid.R
import com.eric.wanandroid.base.ui.BaseActivity
import com.eric.wanandroid.base.ui.BaseFragment
import com.eric.wanandroid.cache.LocalCache
import com.eric.wanandroid.module.home.HomeFragment
import com.eric.wanandroid.iview.IMainView
import com.eric.wanandroid.presenter.MainPresenterImpl
import com.eric.wanandroid.module.qa.QAFragment
import com.eric.wanandroid.module.square.SquareFragment
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
    private val fragments = arrayOf<BaseFragment>(
        HomeFragment(), SquareFragment(), QAFragment()
    )
    private val titles = arrayOf<String>(
        "首页", "广场", "问答"
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
        when (item.itemId){
            R.id.menu_home -> {
                index = 0
            }
            R.id.menu_square -> {
                index = 1
            }
            R.id.menu_qa -> {
                index = 2
            }
        }
        i("choose $index")
        if (index >= fragments.size){
            return true
        }
        switchFragment(index)
        return true
    }

    /**
     * 切换fragment
     */
    private fun switchFragment(
        index: Int
    ){
        tool_bar.title = titles[index]
        ActivityUtils.switchFragment(supportFragmentManager, curFragment, fragments[index], titles[index], R.id.main_frame_layout)
        curFragment = fragments[index]
    }
}