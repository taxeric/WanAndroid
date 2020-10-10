package com.eric.wanandroid.utils

import android.content.Context
import com.eric.wanandroid.base.Config

/**
 * Created by eric on 20-10-10
 */
class DpPxUtils {

    companion object{

        fun dp2px(
            dpValue: Float
        ): Int{
            return (dpValue * Config.DENSITY + 0.5f).toInt()
        }

        fun px2dp(
            pxValue: Float
        ): Int{
            return (pxValue / Config.DENSITY + 0.5f).toInt()
        }
    }
}