package com.eric.wanandroid.base.net

/**
 * Created by eric on 20-10-15
 */
data class BaseEntity(
    val `data`: Any,
    val errorCode: Int,
    val errorMsg: String
)