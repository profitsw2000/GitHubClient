package ru.profitsw2000.githubclient.domain

import io.reactivex.rxjava3.core.Single
import ru.profitsw2000.githubclient.data.web.entities.UserDTO
import ru.profitsw2000.githubclient.data.web.entities.UserDetailsDTO
import ru.profitsw2000.githubclient.data.web.entities.UserRepoDTO

interface WebRepository : RepositoryUseCase {

    fun getRxUserList(): Single<List<UserDTO>>

    fun getRxUserList(fromId: Int): Single<List<UserDTO>>

    fun getRxUserInfo(login: String): Single<UserDetailsDTO>

    fun getRxUserRepositories(login: String): Single<List<UserRepoDTO>>
}