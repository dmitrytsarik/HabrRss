package com.raphanum.habrrss

import android.app.Application
import com.raphanum.habrrss.di.AppComponent
import com.raphanum.habrrss.di.AppModule
import com.raphanum.habrrss.di.DaggerAppComponent

class App : Application() /*: DaggerApplication(), HasActivityInjector*/ {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
    }

//    val component: AppComponent by lazy {
//        DaggerAppComponent.builder()
//            .application(this)
//            .build()
//    }
//
//    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
}