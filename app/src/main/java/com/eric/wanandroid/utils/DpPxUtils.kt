package com.eric.wanandroid.utils

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import com.eric.wanandroid.base.Config

/**
 * Created by eric on 20-10-10
 */
class DpPxUtils {

    companion object{

        private val DISPLAY_METRICS = Resources.getSystem().displayMetrics

        fun dp2px(dpValue: Float): Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, DISPLAY_METRICS);
    }
}