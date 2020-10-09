package com.eric.wanandroid.bean


/**
 * Created by eric on 20-9-22
 */
data class PersonalInfoEntity(
    val `data`: PersonalDetailData,
    val errorCode: Int,
    val errorMsg: String
)

data class PersonalDetailData(
    val coinCount: Int,
    val level: Int,
    val rank: String,
    val userId: Int,
    val username: String
)