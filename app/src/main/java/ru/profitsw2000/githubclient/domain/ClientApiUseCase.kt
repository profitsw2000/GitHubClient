package ru.profitsw2000.githubclient.domain

import androidx.annotation.MainThread
import io.reactivex.rxjava3.core.Single
import ru.profitsw2000.githubclient.domain.entities.User
import ru.profitsw2000.githubclient.domain.entities.UserProfile

interface ClientApiUseCase {
    fun getUserList(@MainThread callback: (List<UserProfile>?) -> Unit)

    fun getRxUserList(): Single<List<User>>
}