package com.eric.wanandroid.utils

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager


/**
 * Created by eric on 20-9-21
 */
class StatusBarUtils {

    companion object{
        fun setActivityAdapter(activity: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                activity.window.statusBarColor = Color.parseColor("#00000000")
                //防止底部虚拟导航栏透明化
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
        }

        fun setStatusBarColor(activity: Activity, color: Int){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                val window = activity.window
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = color
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            }
        }
    }
}