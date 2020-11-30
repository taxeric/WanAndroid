package com.eric.wanandroid.module.square.adapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eric.wanandroid.R
import com.eric.wanandroid.base.RvListener
import com.eric.wanandroid.bean.QADataX
import com.eric.wanandroid.bean.SquareDataX
import kotlinx.android.synthetic.main.rv_item_footer.view.*
import kotlinx.android.synthetic.main.rv_item_qa_normal.view.*
import kotlinx.android.synthetic.main.rv_item_square_normal.view.*

/**
 * Created by eric on 20-10-10
 */
class SquareRvAdapter constructor(
    private val context: Context,
    private val mutableList: MutableList<SquareDataX>,
    private val setFootViewText: SetFootViewText,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<RecyclerView.ViewHolder>(), View.OnClickListener {

    private val collected = R.drawable.ic_favorite_color
    private val noCollected = R.drawable.ic_favorite_border

    private var listener: RvListener.OnItemClickLoadMoreListener ?= null
    private var collectedListener: RvListener.OnCollectedItemListener ?= null

    fun setItemClickListener(listener: RvListener.OnItemClickLoadMoreListener){
        this.listener = listener
    }

    fun setCollectArticleListener(collectedListener: RvListener.OnCollectedItemListener){
        this.collectedListener = collectedListener
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
                LayoutInflater.from(context).inflate(R.layout.rv_item_square_normal, parent, false)
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
                val entity = mutableList[position]
                holder.itemView.let {
                    it.rv_square_article_isnew.visibility = if (entity.fresh) View.VISIBLE else View.GONE
                    it.rv_square_article_author.text = entity.shareUser
                    it.rv_square_article_nice_date.text = entity.niceDate
                    it.rv_square_article_title.text = Html.fromHtml(entity.title)
                    it.rv_square_article_chapter.text = "${entity.superChapterName}·${entity.chapterName}"
                    it.rv_square_article_collected.setImageResource(if (entity.collect) collected else noCollected)
                    it.setOnClickListener(this)
                    it.rv_square_article_collected.setOnClickListener {
                        if (collectedListener != null){
                            collectedListener!!.collected(position)
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

    override fun onClick(v: View) {
        listener?.let {
            val index = recyclerView.getChildAdapterPosition(v)
            if (index != itemCount - 1) {
                it.onItemClick(index, false)
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