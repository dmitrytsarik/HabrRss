package com.raphanum.habrrss.model

import io.reactivex.Single

interface HabrRepository {

    fun getFeeds(): Single<List<HabrItem>>
}