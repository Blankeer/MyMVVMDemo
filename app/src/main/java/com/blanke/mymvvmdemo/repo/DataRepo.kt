package com.blanke.mymvvmdemo.repo

import com.blanke.mymvvmdemo.api.ApiService
import com.blanke.mymvvmdemo.bean.News
import com.blanke.mymvvmdemo.bean.NewsListResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by blanke on 2017/11/23.
 */
class DataRepo(private val apiService: ApiService) {

    companion object {
        @Volatile
        private var instance: DataRepo? = null

        fun getInstance(apiService: ApiService): DataRepo {
            if (instance == null) {
                synchronized(DataRepo::class) {
                    if (instance == null) {
                        instance = DataRepo(apiService)
                    }
                }
            }
            return instance!!
        }
    }

    fun getNewsList(): Observable<NewsListResponse> {
        return apiService.getNewsList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getNewsDetail(id: Int): Observable<News> {
        return apiService.getNewsDetail(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }
}