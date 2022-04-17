package ru.profitsw2000.githubclient.ui

import androidx.annotation.MainThread
import ru.profitsw2000.githubclient.domain.entities.User
import ru.profitsw2000.githubclient.domain.entities.UserProfile
import ru.profitsw2000.githubclient.utils.Publisher

interface ViewModel {
    val showProgress: Publisher<Boolean>
    val getUserProfileList: Publisher<List<UserProfile>>
    val getUserList: Publisher<List<User>>
    val errorCode: Publisher<Int?>

    @MainThread
    fun onLoadUserList()

    fun onLoadRxUserList()

}