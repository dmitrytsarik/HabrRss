package com.raphanum.habrrss.model

import io.reactivex.Single

class HabrRepositoryImpl(private val api: HabrRssApi) : HabrRepository {

    override fun getFeeds(): Single<List<HabrItem>> {
        return api.getAll()
            .map {
                it.channel?.items ?: listOf()
            }
    }
}