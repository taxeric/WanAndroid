package com.eric.wanandroid.bean

/**
 * Created by eric on 20-10-16
 */
data class HotSearchEntity(
    val `data`: List<HotSearchEntityData>,
    val errorCode: Int,
    val errorMsg: String
)

data class HotSearchEntityData(
    val id: Int,
    val link: String,
    val name: String,
    val order: Int,
    val visible: Int
)