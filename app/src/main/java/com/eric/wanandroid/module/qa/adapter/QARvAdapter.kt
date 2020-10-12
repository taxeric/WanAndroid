package com.eric.wanandroid.module.qa.adapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eric.wanandroid.R
import com.eric.wanandroid.base.RvListener
import com.eric.wanandroid.bean.QADataX
import kotlinx.android.synthetic.main.rv_item_footer.view.*
import kotlinx.android.synthetic.main.rv_item_qa_normal.view.*

/**
 * Created by eric on 20-10-10
 */
class QARvAdapter constructor(
    private val context: Context,
    private val mutableList: MutableList<QADataX>,
    private val setFootViewText: SetFootViewText
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val collected = R.drawable.ic_favorite_color
    private val noCollected = R.drawable.ic_favorite_border

    private var listener: RvListener.OnItemClickLoadMoreListener ?= null

    fun setItemClickListener(listener: RvListener.OnItemClickLoadMoreListener){
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
                LayoutInflater.from(context).inflate(R.layout.rv_item_qa_normal, parent, false)
            )
        else
            FootHolder(
                LayoutInflater.from(
                    context
                ).inflate(R.layout.rv_item_footer, parent, false)
            )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NormalViewHolder){
            if (position != mutableList.size) {
                holder.itemView.let {
                    it.rv_qa_article_author.text = mutableList[position].author
                    it.rv_qa_article_nice_date.text = mutableList[position].niceDate
                    it.rv_qa_article_title.text = Html.fromHtml(mutableList[position].title)
                    it.rv_qa_article_desc.text = Html.fromHtml(mutableList[position].desc).toString().replace("\n", "")
                    it.rv_qa_tag_name.text = mutableList[position].tags[0].name
                    it.rv_qa_article_chapter.text = "${mutableList[position].tags[1].name}·${mutableList[position].chapterName}"
                    it.rv_qa_article_collected.setImageResource(if (mutableList[position].collect) collected else noCollected)
                    it.setOnClickListener {
                        if (listener != null){
                            listener!!.onItemClick(position, false)
                        }
                    }
                }
            }
        } else {
            holder.itemView.click_load_more.text = setFootViewText.loadComplete()
            holder.itemView.setOnClickListener{
                if (listener != null){
                    holder.itemView.click_load_more.text = setFootViewText.loading()
                    listener!!.onItemClick(position, true)
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