package com.eric.wanandroid.base


/**
 * Created by eric on 20-9-22
 */
interface RvListener {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    interface OnItemClickLoadMoreListener {
        fun onItemClick(position: Int, loadMore: Boolean)
    }

    interface OnCollectedItemListener{
        fun collected(position: Int)
    }
}