package com.eric.wanandroid.module.qa

import androidx.recyclerview.widget.RecyclerView
import com.eric.wanandroid.R
import com.eric.wanandroid.base.RvListener
import com.eric.wanandroid.base.mvp.BasePresenter
import com.eric.wanandroid.base.ui.BaseFragment
import com.eric.wanandroid.iview.IQAView
import com.eric.wanandroid.presenter.QAPresenterImpl
import kotlinx.android.synthetic.main.fragment_qa.*

/**
 * Created by eric on 20-10-10
 */
class QAFragment: BaseFragment(), IQAView, RvListener.OnItemClickListener {
    override fun setLayout(): Int = R.layout.fragment_qa

    override fun installPresenter(): BasePresenter = QAPresenterImpl(this, context!!, this)

    override fun getRv(): RecyclerView = qa_rv

    override fun initData() {
        (presenter as QAPresenterImpl).getQAList()
    }

    override fun updateQAList(isSuccessful: Boolean) {
        if (!isSuccessful){
            showToast("数据获取失败")
        }
    }

    override fun onItemClick(position: Int) = (presenter as QAPresenterImpl).getQAList()
}