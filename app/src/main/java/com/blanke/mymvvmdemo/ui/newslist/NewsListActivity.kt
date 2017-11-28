package com.blanke.mymvvmdemo.ui.newslist

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.blanke.mymvvmdemo.R
import com.blanke.mymvvmdemo.ViewModelFactory
import com.blanke.mymvvmdemo.databinding.ActivityNewsListBinding
import com.shundaojia.live.Live
import kotlinx.android.synthetic.main.activity_news_list.*

class NewsListActivity : AppCompatActivity() {
    private lateinit var newsListBinding: ActivityNewsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsListBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_list)
        newsListBinding.viewModel = ViewModelProviders
                .of(this, ViewModelFactory())
                .get(NewsListViewModel::class.java)
        with(newsListBinding.viewModel) {
            if (this == null) return@with
            taskListCommand
                    .switchToLatest()
                    .compose(Live.bindLifecycle(this@NewsListActivity))
                    .subscribe({
                        newsList.clear()
                        newsList.addAll(it.stories)
                        Log.d("newsList", "data=${newsList.size}")
                    })
            taskListCommand
                    .errors()
                    .compose(Live.bindLifecycle(this@NewsListActivity))
                    .subscribe {
                        Log.e("newsList", "", it)
                    }
            taskListCommand
                    .executing()
                    .compose(Live.bindLifecycle(this@NewsListActivity))
                    .subscribe {
                        pb_news.visibility = if (it) View.VISIBLE else View.GONE
                        rv_news.visibility = if (!it) View.VISIBLE else View.GONE
                    }
            taskListCommand.execute(0)
        }
    }
}
