package com.eric.wanandroid.module.square

import androidx.recyclerview.widget.RecyclerView
import com.eric.wanandroid.R
import com.eric.wanandroid.base.RvListener
import com.eric.wanandroid.base.mvp.BasePresenter
import com.eric.wanandroid.base.ui.BaseFragment
import com.eric.wanandroid.iview.ISquareView
import com.eric.wanandroid.presenter.SquarePresenterImpl
import kotlinx.android.synthetic.main.fragment_square.*

/**
 * Created by eric on 20-10-10
 */
class SquareFragment: BaseFragment(), ISquareView,
    RvListener.OnItemClickLoadMoreListener,
    RvListener.OnCollectedItemListener{

    override fun setLayout(): Int = R.layout.fragment_square

    override fun installPresenter(): BasePresenter =
        SquarePresenterImpl(this, context!!, this, this)

    override fun getRv(): RecyclerView = square_rv

    override fun initData() {
        (presenter as SquarePresenterImpl).getSquareArticle()
    }

    override fun updateSquareArticle(isSuccessful: Boolean) {
        if (!isSuccessful){
            showToast("数据获取失败")
        }
    }

    override fun onItemClick(position: Int, loadMore: Boolean) {
        if (loadMore) {
            (presenter as SquarePresenterImpl).getSquareArticle()
        } else {
            (presenter as SquarePresenterImpl).toWebView(position)
        }
    }

    override fun collected(position: Int) = (presenter as SquarePresenterImpl).collected(position)
}