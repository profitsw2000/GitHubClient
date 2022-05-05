package ru.profitsw2000.githubclient

import android.app.Application
import android.content.Context
import ru.profitsw2000.githubclient.di.AppComponent
import ru.profitsw2000.githubclient.di.DaggerAppComponent
import ru.profitsw2000.githubclient.di.WebModule

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        //Dagger 2
        appComponent = DaggerAppComponent.builder()
            .webModule(WebModule(this))
            .build()
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }