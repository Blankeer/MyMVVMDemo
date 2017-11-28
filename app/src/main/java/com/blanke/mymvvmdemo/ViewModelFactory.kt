package com.blanke.mymvvmdemo

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.blanke.mymvvmdemo.api.ApiService
import com.blanke.mymvvmdemo.repo.DataRepo
import com.blanke.mymvvmdemo.ui.newslist.NewsItemViewModal
import com.blanke.mymvvmdemo.ui.newslist.NewsListViewModel


/**
 * Created by blanke on 2017/11/23.
 */
class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsListViewModel::class.java)) {
            return NewsListViewModel(DataRepo.getInstance(ApiService.Manager.getApiService()!!)) as T
        } else if (modelClass.isAssignableFrom(NewsItemViewModal::class.java)) {
            return NewsItemViewModal() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}