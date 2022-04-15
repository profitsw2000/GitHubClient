package ru.profitsw2000.githubclient.data

import ru.profitsw2000.githubclient.data.repository.LocalRepoImpl
import ru.profitsw2000.githubclient.domain.ClientApi
import ru.profitsw2000.githubclient.domain.entities.UserProfile

class TestClientApiImpl : ClientApi {
    private val localRepo = LocalRepoImpl()

    override fun getUserList(): List<UserProfile>? {
        val userList = localRepo.getAllUsers()
        Thread.sleep(5_000)
        return userList
    }

    override fun createNewUser(
        name: String,
        info: String,
        city: String,
        avatarUrl: String
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun changeUserInfo(id: Int, info: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun changeUserCity(id: Int, info: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun changeUserAvatar(info: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun createUserRepository(userRepository: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteUserRepository(userRepository: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteUserFromDatabase(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}