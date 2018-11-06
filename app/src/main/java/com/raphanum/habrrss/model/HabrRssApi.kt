package com.raphanum.habrrss.model

import io.reactivex.Single
import retrofit2.http.GET

interface HabrRssApi {

    @GET("/rss/hubs/all")
    fun getAll(): Single<RssContainer>
}