package com.raphanum.habrrss.di

import com.raphanum.habrrss.ui.MainActivity
import com.raphanum.habrrss.presentation.presenter.FeedListPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    fun getPresenter(): FeedListPresenter

//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun application(application: Application): Builder
//
//        fun build(): AppComponent
//    }
//
//    fun inject(app: App)
}