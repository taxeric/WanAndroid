package com.eric.wanandroid.cache

import com.eric.wanandroid.base.Config
import com.eric.wanandroid.bean.HotSearchEntityData
import com.eric.wanandroid.bean.LoginResultData
import com.eric.wanandroid.bean.PersonalDetailData
import com.eric.wanandroid.utils.LogUtils
import com.eric.wanandroid.utils.ShareUtils


/**
 * Created by eric on 20-9-22
 */
class LocalCache {

    companion object{

        private var isLogin = ShareUtils.getInstance().getBoolean(Config.SP_KEY_IS_LOGIN)
        private var saveCookie: HashSet<String> ?= null
        private var userData: PersonalDetailData ?= null

        var updateCookie = false
        var hotSearch = mutableListOf<HotSearchEntityData>()
        var userHeadImg = ShareUtils.getInstance().getString(Config.SP_KEY_USERHEAD_IMG_ID)

        private var username = ""

        fun setUserName(unm: String){
            this.username = unm
            ShareUtils.getInstance().put(Config.SP_KEY_USERNAME, unm)
        }

        fun getUserName(): String{
            if (username.isEmpty()){
                username = ShareUtils.getInstance().getString(Config.SP_KEY_USERNAME)
            }
            return username
        }

        fun setUserData(userData: PersonalDetailData){
            this.userData = userData
        }

        fun getLoginStatus(isRequest: Boolean): Boolean{
            if (isRequest){
                isLogin = ShareUtils.getInstance().getBoolean(Config.SP_KEY_IS_LOGIN)
            }
            return isLogin
        }

        fun getCookie(): HashSet<String>?{
            if (saveCookie == null){
                LogUtils.i("加载本地cookie  reason -> 内存为空")
                val i = ShareUtils.getInstance().getStringSet(Config.SP_KEY_COOKIE) ?: return null
                saveCookie = i as HashSet<String>
            }
            if (updateCookie) {
                LogUtils.i("加载本地cookie  reason -> 更新cookie")
                val i = ShareUtils.getInstance().getStringSet(Config.SP_KEY_COOKIE) ?: return null
                saveCookie = i as HashSet<String>
            }
            return saveCookie
        }

        fun setLoginStatus(isLogin: Boolean){
            ShareUtils.getInstance().put(Config.SP_KEY_IS_LOGIN, isLogin)
            this.isLogin = isLogin
        }
    }
}