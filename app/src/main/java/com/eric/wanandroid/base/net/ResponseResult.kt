package com.eric.wanandroid.base.net

import com.eric.wanandroid.utils.LogUtils


/**
 * Created by eric on 20-9-22
 */
interface ResponseResult<T> {

    fun onSuccess(t: T)
    fun onFail(code: Int, msg: String){
        LogUtils.e("code = $code & msg = $msg")
    }
}