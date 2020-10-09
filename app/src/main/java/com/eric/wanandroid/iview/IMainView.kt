package com.eric.wanandroid.iview

import com.eric.wanandroid.base.mvp.BaseView


/**
 * Created by eric on 20-9-22
 */
interface IMainView: BaseView {

    /**
     * 设置头像图片
     */
    fun setHeaderImg()

    /**
     * 设置名字文本
     */
    fun setHeaderName(name: String)

    /**
     * 设置排名文本
     */
    fun setHeaderRanking(ranking: String)
}