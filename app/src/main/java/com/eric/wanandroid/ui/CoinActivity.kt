package com.eric.wanandroid.ui

import android.annotation.SuppressLint
import android.view.MenuItem
import com.eric.wanandroid.R
import com.eric.wanandroid.base.ui.BaseActivity
import com.eric.wanandroid.bean.PersonalDetailData
import com.eric.wanandroid.cache.LocalCache
import kotlinx.android.synthetic.main.activity_coin.*

/**
 * Created by eric on 20-11-30
 */
class CoinActivity: BaseActivity() {

    override fun setLayout(): Int = R.layout.activity_coin

    @SuppressLint("SetTextI18n")
    override fun initData() {
        setSupportActionBar(tool_bar)
        LocalCache.getUserData()?.let {
            show_mine_coin.text ="用户名：${LocalCache.getUserName()}\n" +
                    "用户ID：${it.userId}"
                    "总积分：${it.coinCount}\n" +
                    "等级：${it.level}\n" +
                    "排名：${it.rank}"
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return true
    }
}