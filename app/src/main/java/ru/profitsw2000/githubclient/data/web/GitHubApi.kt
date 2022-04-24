package ru.profitsw2000.githubclient.data.web

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.profitsw2000.githubclient.data.web.entities.UserDTO
import ru.profitsw2000.githubclient.data.web.entities.UserDetailsDTO
import ru.profitsw2000.githubclient.data.web.entities.UserRepoDTO

interface GitHubApi {

    @GET("users")
    fun listUsers(): Single<List<UserDTO>>

    @GET("users")
    fun listUsers(@Query("since") since: Int): Single<List<UserDTO>>

    @GET("users/{login}")
    fun userInfo(@Path("login") login: String): Single<UserDetailsDTO>

    @GET("users/{login}/repos")
    fun listRepos(@Path("login") login: String): Single<List<UserRepoDTO>>
}