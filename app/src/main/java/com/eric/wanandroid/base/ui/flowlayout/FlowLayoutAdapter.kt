package com.eric.wanandroid.base.ui.flowlayout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.eric.wanandroid.R
import com.eric.wanandroid.bean.HotSearchEntityData

/**
 * Created by eric on 20-10-16
 */
class FlowLayoutAdapter constructor(
    private val context: Context,
    private val data: MutableList<HotSearchEntityData>
) {

    fun notifyDataSetChanged(){
        FlowLayoutAdapter.notifyDataSetChanged()
    }

    fun getCount() = data.size

    fun getView(position: Int): View{
        val textView = LayoutInflater.from(context).inflate(R.layout.tag_layout, null) as TextView
        textView.text = data[position].name
        return textView
    }

    companion object{

        private val list = mutableListOf<OnTagUpdateListener>()

        fun addReObj(listener: OnTagUpdateListener){
            if (!list.contains(listener)){
                list.add(listener)
            }
        }

        fun removeReObj(listener: OnTagUpdateListener){
            list.remove(listener)
        }

        fun clearReObj(){
            if (list.isEmpty()){
                return
            }
            list.clear()
        }

        fun notifyDataSetChanged(){
            if (list.isEmpty()){
                return
            }
            for (i in list){
                i.refresh()
            }
        }
    }
}