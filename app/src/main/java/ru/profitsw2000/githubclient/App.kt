package ru.profitsw2000.githubclient

import android.app.Application
import android.content.Context
import ru.profitsw2000.githubclient.data.web.WebRepositoryImpl
import ru.profitsw2000.githubclient.domain.RepositoryUseCase

class App : Application() {
    val repositoryUseCase: RepositoryUseCase by lazy { WebRepositoryImpl() }
    //val repositoryUseCase: MockRepositoryImpl by lazy { MockRepositoryImpl() }
=======
import android.os.Handler
import android.os.Looper
import ru.profitsw2000.githubclient.data.ClientApiUseCaseImpl
import ru.profitsw2000.githubclient.data.TestClientApiImpl
import ru.profitsw2000.githubclient.domain.ClientApi
import ru.profitsw2000.githubclient.domain.ClientApiUseCase

class App : Application() {
    private val clientApi: ClientApi by lazy { TestClientApiImpl() }
    val clientApiUseCase: ClientApiUseCase by lazy {
        ClientApiUseCaseImpl(app.clientApi, Handler(Looper.getMainLooper()))
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }