package com.eric.wanandroid.utils

import android.content.Context
import android.content.SharedPreferences
import com.eric.wanandroid.base.Config

/**
 * Created by eric on 20-9-22
 */
class ShareUtils private constructor(
        context: Context
){

    private var sp: SharedPreferences = context.getSharedPreferences(Config.LOCAL_SP_NAME, Context.MODE_PRIVATE)

    fun put(key: String, any: Any){
        when (any){
            is String -> sp.edit().putString(key, any).apply()
            is Boolean -> sp.edit().putBoolean(key, any).apply()
            is Int -> sp.edit().putInt(key, any).apply()
            is Set<*> -> sp.edit().putStringSet(key, any as Set<String>).apply()
        }
    }

    fun getString(key: String): String{
        return sp.getString(key, "")!!
    }

    fun getBoolean(key: String): Boolean{
        return sp.getBoolean(key, false)
    }

    fun getInt(key: String): Int{
        return sp.getInt(key, Config.FAIL)
    }

    fun getStringSet(key: String): Set<String>? {
        return sp.getStringSet(key, null)
    }

    companion object{
        private var INSTANCE: ShareUtils?= null

        fun init(context: Context){
            if (INSTANCE == null){
                synchronized(ShareUtils::class) {
                    if (INSTANCE == null) {
                        INSTANCE = ShareUtils(context)
                    }
                }
            }
        }

        fun getInstance(): ShareUtils {
            return INSTANCE!!
        }
    }
}