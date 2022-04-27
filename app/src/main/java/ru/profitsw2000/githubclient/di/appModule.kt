package ru.profitsw2000.githubclient.di

import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.profitsw2000.githubclient.data.local.entities.UserProfile
import ru.profitsw2000.githubclient.data.web.GitHubApi
import ru.profitsw2000.githubclient.data.web.WebRepositoryImpl
import ru.profitsw2000.githubclient.data.web.entities.UserDTO
import ru.profitsw2000.githubclient.data.web.entities.UserDetailsDTO
import ru.profitsw2000.githubclient.data.web.entities.UserRepoDTO
import ru.profitsw2000.githubclient.domain.RepositoryUseCase
import ru.profitsw2000.githubclient.ui.screens.main.MainViewModel
import ru.profitsw2000.githubclient.utils.Publisher

val appModule = module {

    single<String>(named("api_url")) { "https://api.github.com/" }
    single<GitHubApi> { get<Retrofit>().create(GitHubApi::class.java) }
    single<RepositoryUseCase> { WebRepositoryImpl(get()) }
    single { Retrofit.Builder()
        .baseUrl(get<String>(named("api_url")))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(get())
        .build() }
    single<MutableList<UserDTO>> { mutableListOf() }
    single<CompositeDisposable> { CompositeDisposable() }

    factory<Converter.Factory> { GsonConverterFactory.create() }
    factory<Publisher<Boolean>>(named("showProgress")) { Publisher() }
    factory<Publisher<List<UserProfile>>>(named("getUserProfileList")) { Publisher() }
    factory<Publisher<List<UserDTO>>>(named("getUserList")) { Publisher() }
    factory<Publisher<Int?>>(named("errorCode")) { Publisher() }
    factory<Publisher<List<UserRepoDTO>>>(named("getUserRepoList")) { Publisher() }
    factory<Publisher<UserDetailsDTO>>(named("getUserInfo")) { Publisher() }

}

/*
override val getUserRepoList: Publisher<List<UserRepoDTO>> = Publisher()
override val getUserInfo: Publisher<UserDetailsDTO> = Publisher()*/
