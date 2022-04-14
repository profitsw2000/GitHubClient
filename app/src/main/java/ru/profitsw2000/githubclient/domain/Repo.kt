package ru.profitsw2000.githubclient.domain

import ru.profitsw2000.githubclient.domain.entities.UserProfile

interface Repo {
    // Create
    fun addUser(user: UserProfile)
    fun addUserRepository(id: Int, userRepositoryName: String)

    // Read
    fun getAllUsers(): List<UserProfile>

    // Update
    fun changeUser(id: Int, user: UserProfile)
    fun changeUserRepository(id: Int, userRepositoryName: String)

    // Delete
    fun deleteUser(id: Int)
    fun deleteUserRepository(id: Int, userRepositoryName: String)
    fun deleteAll()
}