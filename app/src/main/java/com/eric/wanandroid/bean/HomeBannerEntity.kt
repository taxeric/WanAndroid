package com.eric.wanandroid.bean


/**
 * Created by eric on 20-9-22
 */
data class HomeBannerEntity(
    val `data`: List<HomeBannerData>,
    val errorCode: Int,
    val errorMsg: String
)

data class HomeBannerData(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)

fun createNullBannerData(): HomeBannerData{
    return HomeBannerData("", -1, "", -1, -1, "", -1, "")
}
