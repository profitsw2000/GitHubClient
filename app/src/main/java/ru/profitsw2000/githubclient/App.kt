package ru.profitsw2000.githubclient

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.profitsw2000.githubclient.di.AppComponent
import ru.profitsw2000.githubclient.di.DaggerAppComponent
import ru.profitsw2000.githubclient.di.WebModule
import ru.profitsw2000.githubclient.di.appModule

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        //Dagger 2
        appComponent = DaggerAppComponent.builder()
            .webModule(WebModule(this))
            .build()
        // Start Koin
/*        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }*/
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }