package ru.profitsw2000.githubclient.domain

import androidx.annotation.MainThread
import ru.profitsw2000.githubclient.domain.entities.UserProfile

interface ClientApiUseCase {
    fun getUserList(@MainThread callback: (List<UserProfile>?) -> Unit)
}