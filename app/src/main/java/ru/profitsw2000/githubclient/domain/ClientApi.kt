package ru.profitsw2000.githubclient.domain

import androidx.annotation.WorkerThread
import io.reactivex.rxjava3.core.Single
import ru.profitsw2000.githubclient.domain.entities.User
import ru.profitsw2000.githubclient.domain.entities.UserDetails
import ru.profitsw2000.githubclient.domain.entities.UserProfile
import ru.profitsw2000.githubclient.domain.entities.UserRepo
=======
import ru.profitsw2000.githubclient.domain.entities.UserProfile

interface ClientApi {
    @WorkerThread
    fun getUserList(): List<UserProfile>?

    fun getRxUserList(): Single<List<User>>

    fun getRxUserInfo(login: String): Single<UserDetails>

    fun getRxUserRepositories(login: String): Single<List<UserRepo>>

=======
    @WorkerThread
    fun createNewUser(name: String, info: String, city: String, avatarUrl: String): Boolean

    @WorkerThread
    fun changeUserInfo(id: Int, info: String): Boolean

    @WorkerThread
    fun changeUserCity(id: Int, info: String): Boolean

    @WorkerThread
    fun changeUserAvatar(info: String): Boolean

    @WorkerThread
    fun createUserRepository(userRepository: String): Boolean

    @WorkerThread
    fun deleteUserRepository(userRepository: String): Boolean

    @WorkerThread
    fun deleteUserFromDatabase(id: Int): Boolean
}