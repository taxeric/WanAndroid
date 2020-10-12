package com.eric.wanandroid.bean

/**
 * Created by eric on 20-10-12
 */
data class LoginResultEntity(
    val `data`: LoginResultData,
    val errorCode: Int,
    val errorMsg: String
)

data class LoginResultData(
    val admin: Boolean,
    val chapterTops: List<Any>,
    val coinCount: Int,
    val collectIds: List<Int>,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val publicName: String,
    val token: String,
    val type: Int,
    val username: String
)