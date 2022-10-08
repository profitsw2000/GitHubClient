package ru.profitsw2000.githubclient.domain

import androidx.annotation.MainThread
import ru.profitsw2000.githubclient.data.local.entities.UserProfile
import ru.profitsw2000.githubclient.data.web.entities.UserDetailsDTO
import ru.profitsw2000.githubclient.data.web.entities.UserRepoDTO

interface MockRepository : RepositoryUseCase {
    fun getUserList(): List<UserProfile>

    fun getMockUserInfo(login: String): UserDetailsDTO

    fun getMockUserRepositories(login: String): List<UserRepoDTO>
}