package ru.profitsw2000.githubclient.data.retrofit

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.profitsw2000.githubclient.domain.entities.User
import ru.profitsw2000.githubclient.domain.entities.UserDetails
import ru.profitsw2000.githubclient.domain.entities.UserRepo

interface GitHubApi {
    @GET("users")
    fun listUsers(): Single<List<User>>

    @GET("users")
    fun listUsers(@Query("since") since: Int): Single<List<User>>

    @GET("users/{login}")
    fun userInfo(@Path("login") login: String): Single<UserDetails>

    @GET("users/{login}/repos")
    fun listRepos(@Path("login") login: String): Single<List<UserRepo>>
}