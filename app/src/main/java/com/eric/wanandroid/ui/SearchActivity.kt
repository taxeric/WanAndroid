package com.eric.wanandroid.ui

import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.eric.wanandroid.R
import com.eric.wanandroid.base.mvp.BasePresenter
import com.eric.wanandroid.base.ui.BaseActivity
import com.eric.wanandroid.base.ui.flowlayout.FlowLayout
import com.eric.wanandroid.base.ui.flowlayout.OnTagCheckListener
import com.eric.wanandroid.iview.ISearchView
import com.eric.wanandroid.presenter.SearchPresenterImpl
import kotlinx.android.synthetic.main.activity_search.*

/**
 * Created by eric on 20-10-16
 */
class SearchActivity: BaseActivity(), ISearchView, OnTagCheckListener, TextWatcher {

    override fun setLayout(): Int = R.layout.activity_search

    override fun initData() {
        setSupportActionBar(tool_bar)
        (presenter as SearchPresenterImpl).getHotSearchData()
    }

    override fun initListener() {
        input_search_content.addTextChangedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tool_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        } else if (item.itemId == R.id.menu_search){
            (presenter as SearchPresenterImpl).search(getSearchContent(), true)
        }
        return true
    }

    override fun installPresenter(): BasePresenter? = SearchPresenterImpl(this, this, this)

    override fun onItemCheck(position: Int) {
        (presenter as SearchPresenterImpl).search(position)
    }

    override fun getFlowLayout(): FlowLayout = flow_layout

    override fun getSearchContent(): String = input_search_content.text.toString()

    override fun setSearchContent(content: String) {
        input_search_content.setText(content)
    }

    override fun disProgress(){
        hot_search_pb.visibility = View.GONE
    }

    override fun afterTextChanged(s: Editable?) {
        if (s!!.isEmpty()){
            (presenter as SearchPresenterImpl).setFlowLayoutVisibility(true)
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun onDestroy() {
        super.onDestroy()
        flow_layout.onDestory()
    }
}