package com.eric.wanandroid.ui

import android.view.MenuItem
import android.view.ViewGroup
import com.eric.wanandroid.R
import com.eric.wanandroid.base.mvp.BasePresenter
import com.eric.wanandroid.base.ui.BaseActivity
import com.just.agentweb.AgentWeb
import kotlinx.android.synthetic.main.activity_web.*

/**
 * Created by eric on 20-10-12
 */
class WebActivity: BaseActivity() {

    private lateinit var agentWeb: AgentWeb

    override fun setLayout(): Int = R.layout.activity_web

    override fun initData() {
        setSupportActionBar(tool_bar)
        val url = intent.getStringExtra("url")
        agentWeb = AgentWeb.with(this).setAgentWebParent(container, ViewGroup.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .createAgentWeb()
            .ready()
            .go(url)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return true
    }
}