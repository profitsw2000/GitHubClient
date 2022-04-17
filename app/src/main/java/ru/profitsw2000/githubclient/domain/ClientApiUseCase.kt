package ru.profitsw2000.githubclient.domain

import androidx.annotation.MainThread
import io.reactivex.rxjava3.core.Single
import ru.profitsw2000.githubclient.domain.entities.User
import ru.profitsw2000.githubclient.domain.entities.UserDetails
import ru.profitsw2000.githubclient.domain.entities.UserProfile
import ru.profitsw2000.githubclient.domain.entities.UserRepo

interface ClientApiUseCase {
    fun getUserList(@MainThread callback: (List<UserProfile>?) -> Unit)

    fun getRxUserList(): Single<List<User>>

    fun getRxUserInfo(login: String): Single<UserDetails>

    fun getRxUserRepositories(): Single<List<UserRepo>>
}