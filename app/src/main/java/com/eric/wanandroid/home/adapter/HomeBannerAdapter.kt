package com.eric.wanandroid.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.eric.wanandroid.R
import com.eric.wanandroid.bean.HomeBannerData
import com.eric.wanandroid.utils.LogUtils
import kotlinx.android.synthetic.main.home_banner_item.view.*


/**
 * Created by eric on 20-9-22
 */
class HomeBannerAdapter constructor(
        private val context: Context,
        private val list: MutableList<HomeBannerData>
): PagerAdapter() {

    private var listener: OnBannerItemClickListener ?= null

    fun setOnBannerClickListener(listener: OnBannerItemClickListener){
        this.listener = listener
    }

    override fun getCount(): Int = list.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.home_banner_item, container, false)
        Glide.with(context).load(list[position].imagePath).into(view.vp_banner_pic)
        view.setOnClickListener {
            if (listener != null) {
                listener!!.onClick(list[position].url)
            }
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        super.destroyItem(container, position, `object`)
        container.removeView(`object` as View)
    }

    interface OnBannerItemClickListener{
        fun onClick(url: String)
    }
}