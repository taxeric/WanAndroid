package com.eric.wanandroid.base.ui.flowlayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.eric.wanandroid.utils.DpPxUtils
import com.eric.wanandroid.utils.LogUtils

/**
 * Created by eric on 20-10-16
 */
class FlowLayout constructor(
    context: Context,
    attributeSet: AttributeSet
): ViewGroup(context, attributeSet), OnTagUpdateListener{

    private val pxDefaultMargin = DpPxUtils.dp2px(10f)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val suggestWidth = MeasureSpec.getSize(widthMeasureSpec)
        val suggestHeightMode = MeasureSpec.getMode(heightMeasureSpec)
        var measureHeight = 0
        if (suggestHeightMode == MeasureSpec.AT_MOST){
            var lastViewWidth = 0
            val childCount = childCount
            for (i in 0 until childCount){
                val childView = getChildAt(i)
                LogUtils.i("child view = $childView")
                val params = childView.layoutParams as MarginLayoutParams
                val lM = if (params.leftMargin == 0) pxDefaultMargin else params.leftMargin
                val rM = if (params.rightMargin == 0) pxDefaultMargin else params.rightMargin
                val tM = if (params.topMargin == 0) pxDefaultMargin else params.topMargin
                val bM = if (params.bottomMargin == 0) pxDefaultMargin else params.bottomMargin
                measureChild(childView, widthMeasureSpec, heightMeasureSpec)
                val childWidth = childView.measuredWidth + lM + rM
                if (childWidth + lastViewWidth > suggestWidth){
                    measureHeight += childView.measuredHeight + tM + bM
                    lastViewWidth = childView.measuredWidth + lM + rM
                } else {
                    lastViewWidth += childWidth
                }
                if (i == childCount - 1){
                    measureHeight += childView.measuredHeight + tM + bM
                }
            }
        } else if (suggestHeightMode == MeasureSpec.EXACTLY){
            measureChildren(widthMeasureSpec, heightMeasureSpec)
            measureHeight = MeasureSpec.getSize(heightMeasureSpec)
        }

        setMeasuredDimension(widthMeasureSpec, measureHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var layoutLeft = l
        var layoutHeight = 0
        val parentWidth = measuredWidth
        val childCount = childCount
        for (i in 0 until childCount){
            mCurrentItemIndex ++
            val childView = getChildAt(i)
            setChildClick(childView)
            val childWidth = childView.measuredWidth
            val childHeight = childView.measuredHeight
            val params = childView.layoutParams as MarginLayoutParams
            val lM = if (params.leftMargin == 0) pxDefaultMargin else params.leftMargin
            val rM = if (params.rightMargin == 0) pxDefaultMargin else params.rightMargin
            val tM = if (params.topMargin == 0) pxDefaultMargin else params.topMargin
            val bM = if (params.bottomMargin == 0) pxDefaultMargin else params.bottomMargin
            if (childWidth + layoutLeft + lM + rM > parentWidth){
                layoutHeight += childHeight + tM + bM
                layoutLeft = 0
            }
            childView.layout(
                layoutLeft + lM,
                layoutHeight + tM,
                layoutLeft + childWidth + lM,
                layoutHeight + childHeight + tM
            )
            layoutLeft += childWidth + lM + rM
        }
    }

    private var mCurrentItemIndex = -1

    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(super.generateDefaultLayoutParams())
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context!!, attrs)
    }

    override fun generateLayoutParams(p: LayoutParams?): LayoutParams {
        return MarginLayoutParams(p)
    }

    private var tagCheckListener: OnTagCheckListener ?= null
    private var adapter: FlowLayoutAdapter ?= null

    fun setTagCheckListener(tagCheckListener: OnTagCheckListener){
        this.tagCheckListener = tagCheckListener
    }

    fun setAdapter(adapter: FlowLayoutAdapter){
        this.adapter = adapter
        FlowLayoutAdapter.addReObj(this)
        refresh()
    }

    private fun setChildClick(childView: View){
        if (childView.tag == null){
            childView.setTag(mCurrentItemIndex)
        }
        childView.setOnClickListener {
            if (tagCheckListener != null){
                tagCheckListener!!.onItemCheck(childView.getTag() as Int)
            }
        }
    }

    override fun refresh() {
        val count = adapter!!.getCount()
        if (count != 0){
            mCurrentItemIndex = -1
            removeAllViews()
            for (i in 0 until count){
                addView(adapter!!.getView(i))
            }
            requestLayout()
        }
    }

    fun onDestory(){
        FlowLayoutAdapter.clearReObj()
    }
}

