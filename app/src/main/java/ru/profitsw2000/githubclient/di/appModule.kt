package ru.profitsw2000.githubclient.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.profitsw2000.githubclient.data.web.GitHubApi
import ru.profitsw2000.githubclient.data.web.WebRepositoryImpl
import ru.profitsw2000.githubclient.domain.RepositoryUseCase

val appModule = module {

    single<String>(named("api_url")) { "https://api.github.com/" }
    single<GitHubApi> { get<Retrofit>().create(GitHubApi::class.java) }
    single<RepositoryUseCase> { WebRepositoryImpl(get()) }
    single { Retrofit.Builder()
        .baseUrl(get<String>(named("api_url")))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(get())
        .build() }

    factory<Converter.Factory> { GsonConverterFactory.create() }
}

/*
private val retrofit = Retrofit.Builder()
    .baseUrl("https://api.github.com/")
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .build()
private val api: GitHubApi = retrofit.create(GitHubApi::class.java)

val repositoryUseCase: RepositoryUseCase by lazy { WebRepositoryImpl(api) }
//val repositoryUseCase: MockRepositoryImpl by lazy { MockRepositoryImpl() }*/
