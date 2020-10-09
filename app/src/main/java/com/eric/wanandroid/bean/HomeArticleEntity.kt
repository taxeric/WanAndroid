package com.eric.wanandroid.bean
/**
 * Created by eric on 20-9-21
 */
data class HomeArticleEntity(
        val `data`: HomeData,
        val errorCode: Int,
        val errorMsg: String
)

data class HomeData(
        val curPage: Int,
        val datas: List<HomeDataX>,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int
)

data class HomeDataX(
        val apkLink: String,
        val audit: Int,
        val author: String,
        val canEdit: Boolean,
        val chapterId: Int,
        val chapterName: String,
        val collect: Boolean,
        val courseId: Int,
        val desc: String,
        val descMd: String,
        val envelopePic: String,
        val fresh: Boolean,
        val id: Int,
        val link: String,
        val niceDate: String,
        val niceShareDate: String,
        val origin: String,
        val prefix: String,
        val projectLink: String,
        val publishTime: Long,
        val realSuperChapterId: Int,
        val selfVisible: Int,
        val shareDate: Long,
        val shareUser: String,
        val superChapterId: Int,
        val superChapterName: String,
        val tags: List<HomeTag>,
        val title: String,
        val type: Int,
        val userId: Int,
        val visible: Int,
        val zan: Int
)

data class HomeTag(
    val name: String,
    val url: String
)

fun createNullHomeArticleData(): HomeDataX{
    return HomeDataX("", -1, "", false, -1, "", false,
            -1, "", "", "", false, -1, "", "", "",
            "", "", "", -1, -1, -1, -1, "", -1,
            "", mutableListOf(), "", -1, -1, -1, -1)
}

