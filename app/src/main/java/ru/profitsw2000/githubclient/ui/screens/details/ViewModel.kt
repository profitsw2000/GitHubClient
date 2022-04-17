package ru.profitsw2000.githubclient.ui.screens.details

import androidx.annotation.MainThread
import ru.profitsw2000.githubclient.domain.entities.User
import ru.profitsw2000.githubclient.domain.entities.UserProfile
import ru.profitsw2000.githubclient.utils.Publisher

interface ViewModel {
    val showProgress: Publisher<Boolean>
    val getUserRepoList: Publisher<List<UserProfile>>
    val getUserInfo: Publisher<List<User>>
    val errorCode: Publisher<Int?>

    @MainThread
    fun onLoadUserList()

    fun onLoadRxUserList()

    
}