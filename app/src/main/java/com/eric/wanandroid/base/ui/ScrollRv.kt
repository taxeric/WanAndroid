package com.eric.wanandroid.base.ui

import android.content.Context
import android.util.AttributeSet
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by eric on 20-10-10
 */
class ScrollRv constructor(
    context: Context,
    private val attributeSet: AttributeSet
): RecyclerView(context, attributeSet), CoordinatorLayout.AttachedBehavior {
    override fun getBehavior(): CoordinatorLayout.Behavior<*> =
        ScrollRvBehavior(context, attributeSet)
}