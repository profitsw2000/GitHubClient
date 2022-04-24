package ru.profitsw2000.githubclient

import android.app.Application
import android.content.Context
import ru.profitsw2000.githubclient.data.web.WebRepositoryImpl
import ru.profitsw2000.githubclient.domain.RepositoryUseCase

class App : Application() {
    val repositoryUseCase: RepositoryUseCase by lazy { WebRepositoryImpl() }
    //val repositoryUseCase: MockRepositoryImpl by lazy { MockRepositoryImpl() }
}

val Context.app: App
    get() {
        return applicationContext as App
    }