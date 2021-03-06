package com.eric.wanandroid.base


/**
 * Created by eric on 20-9-21
 */
open class Config {

    companion object{
        const val SUCCESS = 1
        const val FAIL = -1

        const val LOCAL_SP_NAME = "localData"
        const val SP_KEY_IS_LOGIN = "isLogin"
        const val SP_KEY_COOKIE = "saveCookie"
        const val SP_KEY_USERNAME = "saveUsername"
        const val SP_KEY_USERHEAD_IMG_ID = "headImgID"

        var DENSITY = 0.0f
    }
}