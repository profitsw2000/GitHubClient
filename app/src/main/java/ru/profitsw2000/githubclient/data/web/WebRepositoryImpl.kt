package ru.profitsw2000.githubclient.data.web

import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.profitsw2000.githubclient.data.web.entities.UserDTO
import ru.profitsw2000.githubclient.data.web.entities.UserDetailsDTO
import ru.profitsw2000.githubclient.data.web.entities.UserRepoDTO
import ru.profitsw2000.githubclient.domain.WebRepository

class WebRepositoryImpl : WebRepository {
    //retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api: GitHubApi = retrofit.create(GitHubApi::class.java)

    override fun getRxUserList(): Single<List<UserDTO>> {
        return api.listUsers()
    }

    override fun getRxUserList(fromId: Int): Single<List<UserDTO>> {
        return api.listUsers(fromId)
    }

    override fun getRxUserInfo(login: String): Single<UserDetailsDTO> {
        return api.userInfo(login)
    }

    override fun getRxUserRepositories(login: String): Single<List<UserRepoDTO>> {
        return api.listRepos(login)
    }
}