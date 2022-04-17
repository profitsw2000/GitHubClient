package ru.profitsw2000.githubclient.data.retrofit

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import ru.profitsw2000.githubclient.domain.entities.User

interface GitHubApi {
    @GET("users")
    fun listUsers(): Single<List<User>>
}