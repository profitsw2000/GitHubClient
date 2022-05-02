package ru.profitsw2000.githubclient.domain

import androidx.annotation.MainThread
import ru.profitsw2000.githubclient.data.local.entities.UserProfile

interface MockRepository : RepositoryUseCase {
    fun getUserList(): List<UserProfile>

    fun searchForUser(userLogin: String): UserProfile
}