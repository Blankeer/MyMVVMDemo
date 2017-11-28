package com.blanke.mymvvmdemo.api

import com.blanke.mymvvmdemo.bean.News
import com.blanke.mymvvmdemo.bean.NewsListResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by blanke on 2017/11/23.
 */
interface ApiService {
    @GET("news/latest")
    fun getNewsList(): Observable<NewsListResponse>

    @GET("news/{id}")
    fun getNewsDetail(@Path("id") id: Int): Observable<News>

    object Manager {
        private val API_HOST: String = "https://news-at.zhihu.com/api/4/"
        private var api: ApiService? = null

        fun getApiService(): ApiService? {
            if (api != null) return api
            val retrofit = Retrofit.Builder()
                    .baseUrl(API_HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            api = retrofit.create(ApiService::class.java)
            return api
        }
    }
}