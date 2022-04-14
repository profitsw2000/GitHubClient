package ru.profitsw2000.githubclient.domain

import androidx.annotation.WorkerThread
import ru.profitsw2000.githubclient.domain.entities.UserProfile

interface ClientApi {
    @WorkerThread
    fun getUserList(): List<UserProfile>

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