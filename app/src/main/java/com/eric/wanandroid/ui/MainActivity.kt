package com.eric.wanandroid.ui

import android.app.Activity
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.eric.wanandroid.R
import com.eric.wanandroid.base.mvp.BasePresenter
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
import kotlinx.android.synthetic.main.activity_main.tool_bar
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.nav_header_layout.view.*

class MainActivity: BaseActivity(), IMainView, NavigationView.OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemSelectedListener, MainPresenterImpl.UpdateListener,
    View.OnClickListener {

    private val REQUEST_CODE = 101
    private var isLogin: Boolean = false

    private lateinit var headerView: View

    private lateinit var curFragment: BaseFragment
    private val fragments = arrayOf<BaseFragment>(
        HomeFragment(), SquareFragment(), QAFragment()
    )
    private val titles = arrayOf(
        "首页", "广场", "问答"
    )

    override fun setLayout(): Int = R.layout.activity_main

    override fun initListener() {
        bottom_nav.setOnNavigationItemSelectedListener(this)
        nav_view.setNavigationItemSelectedListener{
            when (it.itemId){
                R.id.nav_setting    -> SettingActivity().intoActivity(this)
                R.id.nav_star       -> {
                    if (!isLogin){
                        showToast("请先登录")
                    } else {
                        CoinActivity().intoActivity(this)
                    }
                }
            }
            false
        }
    }

    override fun initData() {
        MainPresenterImpl.registerUpdateListener(this)
        setSupportActionBar(tool_bar)
        curFragment = fragments[0]
        ActivityUtils.addFragmentToActivity(supportFragmentManager, fragments[0], "home", R.id.main_frame_layout)
        initDrawerData()
    }

    override fun installPresenter(): BasePresenter = MainPresenterImpl(this)

    /**
     * 初始化侧滑栏数据
     */
    private fun initDrawerData(){
        headerView = nav_view.getHeaderView(0)
        //获取登录状态
        isLogin = LocalCache.getLoginStatus(true)
        if (isLogin){
            (presenter as MainPresenterImpl).getInfo()
        } else {
            headerView.header_name.text = getString(R.string.please_login)
            headerView.header_ranking.text = ""
        }
        setHeaderImg()
        headerView.header_img.setOnClickListener(this)
    }

    private fun login() {
        //如果没有登录则跳转到登录页面
        if (!isLogin){
            toActivityForResult(LoginActivity(), REQUEST_CODE)
        }
    }

    override fun setHeaderImg() {
        val builder = RequestOptions().error(R.drawable.bb)
        Glide.with(this)
            .load("https://q1.qlogo.cn/g?b=qq&nk=${LocalCache.userHeadImg}&s=640")
            .apply(builder)
            .into(headerView.header_img)
    }

    override fun setHeaderName(name: String) {
        headerView.header_name.text = name
    }

    override fun setHeaderRanking(ranking: String) {
        headerView.header_ranking.text = ranking
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK){
                //把是否获取本地cookie的flag置为true，表示重新获取本地cookie
                LocalCache.updateCookie = true
                //重新获取登录状态
                isLogin = LocalCache.getLoginStatus(true)
                (presenter as MainPresenterImpl).getInfo()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tool_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menu_search    -> SearchActivity().intoActivity(this)
        }
        return true
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

    override fun update() {
        setHeaderImg()
    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.header_img -> login()
        }
    }
}