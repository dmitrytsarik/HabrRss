package com.raphanum.habrrss.di

import com.raphanum.habrrss.BuildConfig
import com.raphanum.habrrss.model.HabrRepositoryImpl
import com.raphanum.habrrss.model.HabrRssApi
import com.raphanum.habrrss.model.HabrRepository
import com.raphanum.habrrss.presentation.presenter.FeedListPresenter
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providePresenter(repository: HabrRepository): FeedListPresenter = FeedListPresenter(repository)

    @Provides
    @Singleton
    fun provideRepository(api: HabrRssApi): HabrRepository =
        HabrRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideApi(httpClient: OkHttpClient): HabrRssApi = Retrofit.Builder()
        .client(httpClient)
        .baseUrl("https://habr.com/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()
        .create(HabrRssApi::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE))
        .build()
}