package com.eric.wanandroid.imodelImpl

import com.eric.wanandroid.base.Config
import com.eric.wanandroid.base.net.ResponseResult
import com.eric.wanandroid.base.net.RetrofitUtils
import com.eric.wanandroid.bean.HomeArticleEntity
import com.eric.wanandroid.bean.HomeBannerEntity
import com.eric.wanandroid.imodel.IHomeModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by eric on 20-9-22
 */
@Suppress("UNREACHABLE_CODE")
class IHomeModelImpl: IHomeModel {

    private var bannerCall: Call<HomeBannerEntity> ?= null
    private var articleCall: Call<HomeArticleEntity> ?= null

    override fun getBanner(result: ResponseResult<HomeBannerEntity>) {
        this.bannerCall = RetrofitUtils.getInstance().get().getBanner()
        bannerCall!!.enqueue(object: Callback<HomeBannerEntity>{
            override fun onResponse(
                call: Call<HomeBannerEntity>,
                response: Response<HomeBannerEntity>
            ) {
                if (response.isSuccessful){
                    val bannerEntity = response.body()
                    result.onSuccess(bannerEntity!!)
                } else {
                    result.onFail(response.code(), "get home banner is error")
                }
            }

            override fun onFailure(call: Call<HomeBannerEntity>, t: Throwable) {
                result.onFail(Config.FAIL, t.message!!)
            }
        })
    }

    private var nowPage = 0
    override fun getArticle(result: ResponseResult<HomeArticleEntity>) {
        this.articleCall = RetrofitUtils.getInstance().get().getHomeArticle(this.nowPage.toString())
        articleCall!!.enqueue(object: Callback<HomeArticleEntity>{
            override fun onResponse(
                call: Call<HomeArticleEntity>,
                response: Response<HomeArticleEntity>
            ) {
                if (response.isSuccessful){
                    val homeArticleEntity = response.body()
                    nowPage += 1
                    result.onSuccess(homeArticleEntity!!)
                } else {
                    result.onFail(response.code(), "get home article is error")
                }
            }

            override fun onFailure(call: Call<HomeArticleEntity>, t: Throwable) {
                result.onFail(Config.FAIL, t.message!!)
            }
        })
    }

    override fun onDestory() {
        RetrofitUtils.disCall(bannerCall)
        RetrofitUtils.disCall(articleCall)
    }
}