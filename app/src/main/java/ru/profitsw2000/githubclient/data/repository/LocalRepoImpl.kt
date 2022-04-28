package ru.profitsw2000.githubclient.data.repository

import ru.profitsw2000.githubclient.domain.Repo
import ru.profitsw2000.githubclient.domain.entities.UserProfile

class LocalRepoImpl: Repo {

    private val users: MutableList<UserProfile> =
        mutableListOf(
        UserProfile(0, "Piccolo", "Middle Developer", "San-Francisco","", mutableListOf("Repo1", "Repo2")),
        UserProfile(1, "MorCam", "Senior Developer", "Chickago","", mutableListOf("Repo1", "Repo2")),
        UserProfile(2, "AscorImpact", "Senior Developer", "Deli","", mutableListOf("Repo1", "Repo2")),
        UserProfile(3, "FanReid", "Middle Developer", "Seoul","", mutableListOf("Repo1", "Repo2")),
        UserProfile(4, "ZoccoFear", "Junior Developer", "Bombei","", mutableListOf("Repo1", "Repo2")),
        UserProfile(5, "Caroq", "Senior Developer", "Boston","", mutableListOf("Repo1", "Repo2")),
        UserProfile(6, "Wayscorer", "Senior Developer", "Paris","", mutableListOf("Repo1", "Repo2")),
        UserProfile(7, "Irwitch", "Middle Developer", "Berlin","", mutableListOf("Repo1", "Repo2")),
        UserProfile(8, "Charger001", "Junior Developer", "Melbourn","", mutableListOf("Repo1", "Repo2"))
    )
    override fun addUser(user: UserProfile) {
        users.add(user)
    }

    override fun addUserRepository(id: Int, userRepositoryName: String) {
        for (user in users) {
            if (user.id == id) {user.userRepositories.add(userRepositoryName)}
        }
    }

    override fun getAllUsers(): List<UserProfile> {
        return users
    }

    override fun changeUser(id: Int, user: UserProfile) {
        for (user in users) {
            if (user.id == id) {
                val index = users.indexOf(user)
                users[index] = user
            }
        }
    }

    override fun changeUserRepository(id: Int, userRepositoryName: String) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(id: Int) {
        for (user in users) {
            if (user.id == id) {
                val index = users.indexOf(user)
                users.removeAt(index)
            }
        }
    }

    override fun deleteUserRepository(id: Int, userRepositoryName: String) {

        for (user in users) {
            if (user.id == id) {
                for (userRepo in user.userRepositories) {
                    if (userRepo == userRepositoryName) {
                        val index = user.userRepositories.indexOf(userRepositoryName)
                        user.userRepositories.removeAt(index)
                    }
                }
            }
        }
    }

    override fun deleteAll() {
        users.clear()
    }
}