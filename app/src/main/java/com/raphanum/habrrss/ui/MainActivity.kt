package com.raphanum.habrrss.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.raphanum.habrrss.App
import com.raphanum.habrrss.R
import com.raphanum.habrrss.model.HabrItem
import com.raphanum.habrrss.presentation.presenter.FeedListPresenter
import com.raphanum.habrrss.presentation.view.FeedListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpAppCompatActivity(), FeedListView {

    @InjectPresenter
    lateinit var presenter: FeedListPresenter

    private val feedListAdapter = FeedListAdapter()

    @ProvidePresenter
    fun initPresenter() = App.appComponent.getPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

        setContentView(R.layout.activity_main)
        recyclerView.adapter = feedListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        swipeRefreshView.setOnRefreshListener {
            presenter.onRefresh()
        }
    }

    override fun setData(items: List<HabrItem>) {
        feedListAdapter.setItems(items)
    }

    override fun setLoading(loading: Boolean) {
        swipeRefreshView.isRefreshing = loading
    }
}
