package com.eric.wanandroid.module.home.adapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eric.wanandroid.R
import com.eric.wanandroid.base.RvListener
import com.eric.wanandroid.bean.HomeDataX
import kotlinx.android.synthetic.main.rv_item_footer.view.*
import kotlinx.android.synthetic.main.rv_item_home_normal.view.*

/**
 * Created by eric on 20-9-21
 */
class HomeRvAdapter constructor(
    private val context: Context,
    private val mutableList: MutableList<HomeDataX>,
    private val setFootViewText: SetFootViewText
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val collected = R.drawable.ic_favorite_color
    private val noCollected = R.drawable.ic_favorite_border

    private var listener: RvListener.OnItemClickListener ?= null

    fun setItemClickListener(listener: RvListener.OnItemClickListener){
        this.listener = listener
    }

    private val NORMAL_FLAG = 0
    private val FOOT_FLAG = -1

    class NormalViewHolder constructor(
        view: View
    ): RecyclerView.ViewHolder(view)

    class FootHolder constructor(
        view: View
    ): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == NORMAL_FLAG)
            NormalViewHolder(
                LayoutInflater.from(context).inflate(R.layout.rv_item_home_normal, parent, false)
            )
        else
            FootHolder(
                LayoutInflater.from(context).inflate(R.layout.rv_item_footer, parent, false)
            )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NormalViewHolder){
            if (position != mutableList.size) {
                holder.itemView.let {
                    it.rv_home_article_title.text = Html.fromHtml(mutableList[position].title)
                    it.rv_home_article_new.visibility = if (mutableList[position].fresh) View.VISIBLE else View.GONE
                    it.rv_home_article_qa.visibility = if (mutableList[position].superChapterId == 440) View.VISIBLE else View.GONE
                    it.rv_home_article_share_time.text = "时间：${mutableList[position].niceShareDate}"
                    it.rv_home_article_share_user.text = "分享人：${mutableList[position].shareUser}"
                    it.rv_home_article_collected.setImageResource(if (mutableList[position].collect) collected else noCollected)
                }
            }
        } else {
            holder.itemView.click_load_more.text = setFootViewText.loadComplete()
            holder.itemView.setOnClickListener{
                if (listener != null){
                    holder.itemView.click_load_more.text = setFootViewText.loading()
                    listener!!.onItemClick(position)
                }
            }
        }
    }

    override fun getItemCount(): Int = mutableList.size + 1

    override fun getItemViewType(position: Int): Int = if (position == mutableList.size) FOOT_FLAG else NORMAL_FLAG

    //提供一个接口给外部，用于设置foot文本
    interface SetFootViewText{
        fun loadComplete(): String
        fun loading(): String
    }
}