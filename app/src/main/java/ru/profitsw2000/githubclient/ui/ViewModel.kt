package ru.profitsw2000.githubclient.ui

import androidx.annotation.MainThread
import ru.profitsw2000.githubclient.data.local.entities.UserProfile
import ru.profitsw2000.githubclient.data.web.entities.UserDTO
import ru.profitsw2000.githubclient.utils.Publisher

interface ViewModel {
    val showProgress: Publisher<Boolean>
    val getUserProfileList: Publisher<List<UserProfile>>
    val getUserList: Publisher<List<UserDTO>>
    val errorCode: Publisher<Int?>

    @MainThread
    fun onLoadUserList()

    fun onLoadRxUserList()

    fun onLoadRxUserList(fromId: Int)

    fun onCleared()
}