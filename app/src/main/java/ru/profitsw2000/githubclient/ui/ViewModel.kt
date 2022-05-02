package ru.profitsw2000.githubclient.ui

import androidx.annotation.MainThread
import ru.profitsw2000.githubclient.data.local.entities.UserProfile
import ru.profitsw2000.githubclient.data.web.entities.UserDTO
import ru.profitsw2000.githubclient.data.web.entities.UserDetailsDTO
import ru.profitsw2000.githubclient.utils.Publisher

interface ViewModel {
    val showProgress: Publisher<Boolean>
    val getUserProfileList: Publisher<List<UserProfile>>
    val getUserList: Publisher<List<UserDTO>>
    val getUser: Publisher<UserDetailsDTO>
    val errorCode: Publisher<Int?>

    @MainThread
    fun onLoadUserList()

    fun onLoadRxUserList()

    fun onLoadRxUserList(fromId: Int)

    fun onLoadRxUser(login: String)

    fun onCleared()
}