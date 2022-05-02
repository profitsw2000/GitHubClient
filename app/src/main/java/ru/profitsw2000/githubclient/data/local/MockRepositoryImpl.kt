package ru.profitsw2000.githubclient.data.local

import androidx.annotation.MainThread
import ru.profitsw2000.githubclient.data.local.entities.UserProfile
import ru.profitsw2000.githubclient.domain.MockRepository

class MockRepositoryImpl : MockRepository{

    private val users: MutableList<UserProfile> =
        mutableListOf(
            UserProfile(
                0,
                "Piccolo",
                "Middle Developer",
                "San-Francisco",
                "",
                mutableListOf("Repo1", "Repo2")
            ),
            UserProfile(                1,
                "MorCam",
                "Senior Developer",
                "Chickago",
                "",
                mutableListOf("Repo1", "Repo2")
            ),
            UserProfile(                 2,
                "AscorImpact",
                "Senior Developer",
                "Deli",
                "",
                mutableListOf("Repo1", "Repo2")
            ),
            UserProfile(                 3,
                "FanReid",
                "Middle Developer",
                "Seoul",
                "",
                mutableListOf("Repo1", "Repo2")
            ),
            UserProfile(                 4,
                "ZoccoFear",
                "Junior Developer",
                "Bombei",
                "",
                mutableListOf("Repo1", "Repo2")
            ),
            UserProfile(                 5,
                "Caroq",
                "Senior Developer",
                "Boston",
                "",
                mutableListOf("Repo1", "Repo2")
            ),
            UserProfile(                 6,
                "Wayscorer",
                "Senior Developer",
                "Paris",
                "",
                mutableListOf("Repo1", "Repo2")
            ),
            UserProfile(                 7,
                "Irwitch",
                "Middle Developer",
                "Berlin",
                "",
                mutableListOf("Repo1", "Repo2")
            ),
            UserProfile(                 8,
                "Charger001",
                "Junior Developer",
                "Melbourn",
                "",
                mutableListOf("Repo1", "Repo2")
            )
        )

    override fun getUserList(): List<UserProfile> {
        return users
    }

    override fun searchForUser(userLogin: String): UserProfile {
        TODO("Not yet implemented")
    }


}