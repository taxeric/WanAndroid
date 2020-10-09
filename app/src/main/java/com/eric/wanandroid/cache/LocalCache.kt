package com.eric.wanandroid.cache

import com.eric.wanandroid.base.Config
import com.eric.wanandroid.utils.ShareUtils


/**
 * Created by eric on 20-9-22
 */
class LocalCache {

    companion object{

        var isLogin = ShareUtils.getInstance().getBoolean(Config.SP_KEY_IS_LOGIN)

        fun put(key: String, value: Any){
        }
    }
}