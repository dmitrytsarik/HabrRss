package com.raphanum.habrrss.presentation.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.raphanum.habrrss.model.HabrRepository
import com.raphanum.habrrss.presentation.view.FeedListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class FeedListPresenter(private val repository: HabrRepository) : MvpPresenter<FeedListView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()
    }

    fun onRefresh() {
        loadData()
    }


    private fun loadData() {
        viewState.setLoading(true)
        repository.getFeeds()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("HABR_RSS", "Items: $it")
                viewState.setLoading(false)
                viewState.setData(it)
            },
                {
                    viewState.setLoading(false)
                    it.printStackTrace()
                })
    }
}