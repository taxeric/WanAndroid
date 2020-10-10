package com.eric.wanandroid.base.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout

/**
 * Created by eric on 20-10-10
 */
class ScrollRvBehavior constructor(
    context: Context,
    attributeSet: AttributeSet
): CoordinatorLayout.Behavior<RecyclerView>(context, attributeSet) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: RecyclerView,
        dependency: View
    ): Boolean {
        return dependency is AppBarLayout || super.layoutDependsOn(parent, child, dependency)
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: RecyclerView,
        dependency: View
    ): Boolean {
        val offset = dependency.bottom - child.top
        child.translationY = offset.toFloat()
        return false
    }
}