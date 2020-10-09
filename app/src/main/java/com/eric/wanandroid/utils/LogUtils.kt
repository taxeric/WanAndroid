package com.eric.wanandroid.utils

import android.os.Build
import android.util.Log
import androidx.core.os.BuildCompat
import com.eric.wanandroid.BuildConfig

/**
 * Created by eric on 20-9-21
 */
class LogUtils {

    companion object{

        private const val TAG = "WanAndroid"
        private val DEBUG = BuildConfig.DEBUG

        fun i(msg: String){
            if (DEBUG){
                Log.i(TAG, msg)
            }
        }

        fun e(msg: String){
            if (DEBUG){
                Log.e(TAG, msg)
            }
        }
    }
}