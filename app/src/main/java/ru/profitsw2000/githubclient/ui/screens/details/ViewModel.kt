package ru.profitsw2000.githubclient.ui.screens.details

import androidx.annotation.MainThread
import ru.profitsw2000.githubclient.data.web.entities.UserDetailsDTO
import ru.profitsw2000.githubclient.data.web.entities.UserRepoDTO
import ru.profitsw2000.githubclient.utils.Publisher

interface ViewModel {
    val showProgress: Publisher<Boolean>
    val getUserRepoList: Publisher<List<UserRepoDTO>>
    val getUserInfo: Publisher<UserDetailsDTO>
    val errorCode: Publisher<Int>

    @MainThread
    fun onLoadUserInfo(login: String)

    fun onCleared()
}