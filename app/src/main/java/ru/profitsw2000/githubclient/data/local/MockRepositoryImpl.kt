package ru.profitsw2000.githubclient.data.local

import ru.profitsw2000.githubclient.data.local.entities.UserProfile
import ru.profitsw2000.githubclient.data.web.entities.UserDetailsDTO
import ru.profitsw2000.githubclient.data.web.entities.UserRepoDTO
import ru.profitsw2000.githubclient.domain.MockRepository

class MockRepositoryImpl : MockRepository{

    private val users: MutableList<UserProfile> =
        mutableListOf(
            UserProfile(
                0,
                "Piccolo",
                "Middle Developer",
                "San-Francisco",
                "https://avatars.githubusercontent.com/u/84991333",
                mutableListOf("Repo1", "Repo2")
            ),
            UserProfile(                1,
                "MorCam",
                "Senior Developer",
                "Chickago",
                "https://avatars.githubusercontent.com/u/84991333?s=400&v=4",
                mutableListOf("Repo1", "Repo2")
            ),
            UserProfile(                 2,
                "AscorImpact",
                "Senior Developer",
                "Deli",
                "https://avatars.githubusercontent.com/u/84991333?s=400&v=4",
                mutableListOf("Repo1", "Repo2")
            ),
            UserProfile(                 3,
                "FanReid",
                "Middle Developer",
                "Seoul",
                "https://avatars.githubusercontent.com/u/84991333?s=400&v=4",
                mutableListOf("Repo1", "Repo2")
            ),
            UserProfile(                 4,
                "ZoccoFear",
                "Junior Developer",
                "Bombei",
                "https://avatars.githubusercontent.com/u/84991333?s=400&v=4",
                mutableListOf("Repo1", "Repo2")
            ),
            UserProfile(                 5,
                "Caroq",
                "Senior Developer",
                "Boston",
                "https://avatars.githubusercontent.com/u/84991333?s=400&v=4",
                mutableListOf("Repo1", "Repo2")
            ),
            UserProfile(                 6,
                "Wayscorer",
                "Senior Developer",
                "Paris",
                "https://avatars.githubusercontent.com/u/84991333?s=400&v=4",
                mutableListOf("Repo1", "Repo2")
            ),
            UserProfile(                 7,
                "Irwitch",
                "Middle Developer",
                "Berlin",
                "https://avatars.githubusercontent.com/u/84991333?s=400&v=4",
                mutableListOf("Repo1", "Repo2")
            ),
            UserProfile(                 8,
                "Charger001",
                "Junior Developer",
                "Melbourn",
                "https://avatars.githubusercontent.com/u/84991333?s=400&v=4",
                mutableListOf("Repo1", "Repo2")
            )
        )

    override fun getUserList(): List<UserProfile> {
        return users
    }

    override fun getMockUserInfo(login: String): UserDetailsDTO {

        return when (login) {
            "Piccolo" -> UserDetailsDTO(
                users[0].userName,
                users[0].avatarUrl,
                users[0].userName,
                users[0].userCity
            )
            "MorCam" -> UserDetailsDTO(
                users[1].userName,
                users[1].avatarUrl,
                users[1].userName,
                users[1].userCity
            )
            "AscorImpact" -> UserDetailsDTO(
                users[2].userName,
                users[2].avatarUrl,
                users[2].userName,
                users[2].userCity
            )
            "FanReid" -> UserDetailsDTO(
                users[3].userName,
                users[3].avatarUrl,
                users[3].userName,
                users[3].userCity
            )
            else -> UserDetailsDTO(
                users[0].userName,
                users[0].avatarUrl,
                users[0].userName,
                users[0].userCity
            )
        }
    }

    override fun getMockUserRepositories(login: String): List<UserRepoDTO> {

        val repoList: MutableList<UserRepoDTO> = mutableListOf()

        for (i in 1..50) {
            repoList.add(UserRepoDTO(i,"Repo$i"))
        }
        return repoList
    }
}