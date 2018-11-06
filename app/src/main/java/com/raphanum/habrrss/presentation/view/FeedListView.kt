package com.raphanum.habrrss.presentation.view

import com.arellomobile.mvp.MvpView
import com.raphanum.habrrss.model.HabrItem

interface FeedListView : MvpView {
    fun setData(items: List<HabrItem>)

    fun setLoading(loading: Boolean)
}