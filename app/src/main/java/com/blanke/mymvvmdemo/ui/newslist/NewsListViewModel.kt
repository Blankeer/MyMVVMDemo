package com.blanke.mymvvmdemo.ui.newslist

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import com.blanke.mymvvmdemo.BR
import com.blanke.mymvvmdemo.R
import com.blanke.mymvvmdemo.bean.News
import com.blanke.mymvvmdemo.bean.NewsListResponse
import com.blanke.mymvvmdemo.repo.DataRepo
import com.shundaojia.rxcommand.RxCommand
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList

/**
 * Created by blanke on 2017/11/23.
 */
class NewsListViewModel(private val repo: DataRepo) : ViewModel() {
    val newsList: ObservableArrayList<News> = ObservableArrayList<News>()
    val newsList2: MergeObservableList<News>
    val adapter: BindingRecyclerViewAdapter<News>
    val itemBinding: ItemBinding<News>
    val taskListCommand: RxCommand<NewsListResponse>

    init {
        newsList2 = MergeObservableList<News>()
                .insertList(newsList)
        adapter = BindingRecyclerViewAdapter()
        itemBinding = ItemBinding.of(BR.news, R.layout.item_news)
        taskListCommand = RxCommand.create { repo.getNewsList() }
    }
}