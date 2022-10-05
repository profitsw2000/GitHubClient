package ru.profitsw2000.githubclient.data.local

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import ru.profitsw2000.githubclient.data.local.entities.UserProfile
import ru.profitsw2000.githubclient.data.local.MockRepositoryImpl

internal class MockRepositoryImplTest {

    lateinit var mockRepositoryImpl: MockRepositoryImpl
    lateinit var userList: MutableList<UserProfile>

    @Before
    fun setUp() {
        userList = mutableListOf(
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

        mockRepositoryImpl = MockRepositoryImpl()
    }

    @Test
    fun mockRepository_CorrectUserByIndex_ReturnTrue() {
        assertEquals(mockRepositoryImpl.getUserList()[0], userList[0])
    }

    @Test
    fun mockRepository_WrongUserByIndex_ReturnTrue() {
        assertNotEquals(mockRepositoryImpl.getUserList()[0], userList[8])
    }

    @Test
    fun mockRepository_NotNull_ReturnTrue() {
        assertNotNull(mockRepositoryImpl.getUserList())
    }

    @Test
    fun mockRepository_ArraysEquals_ReturnTrue() {
        assertEquals(mockRepositoryImpl.getUserList(), userList)
    }
}