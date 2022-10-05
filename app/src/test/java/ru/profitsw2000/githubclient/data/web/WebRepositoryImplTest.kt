package ru.profitsw2000.githubclient.data.web

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.profitsw2000.githubclient.data.web.entities.UserDTO
import ru.profitsw2000.githubclient.data.web.entities.UserDetailsDTO
import ru.profitsw2000.githubclient.data.web.entities.UserRepoDTO

private const val id = 5
private const val login = "login"

internal class WebRepositoryImplTest {

    private lateinit var repository: WebRepositoryImpl

    @Mock
    private lateinit var gitHubApi: GitHubApi

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = WebRepositoryImpl(gitHubApi)
    }

    @Test
    fun getRxUserList_Test() {
        Mockito.`when`(gitHubApi.listUsers()).thenReturn(Single.just(listOf(UserDTO("",0,"",""))))
        repository.getRxUserList()
        verify(gitHubApi, times(1)).listUsers()
    }

    @Test
    fun getRxUserListFromId_Test() {
        Mockito.`when`(gitHubApi.listUsers(id)).thenReturn(Single.just(listOf(UserDTO("",0,"",""))))
        repository.getRxUserList(id)
        verify(gitHubApi, times(1)).listUsers(id)
    }

    @Test
    fun getRxUserInfo_Test() {
        Mockito.`when`(gitHubApi.userInfo(login)).thenReturn(Single.just(UserDetailsDTO("","","","")))
        repository.getRxUserInfo(login)
        verify(gitHubApi, times(1)).userInfo(login)
    }

    @Test
    fun getRxUserRepositories_Test() {
        Mockito.`when`(gitHubApi.listRepos(login)).thenReturn(Single.just(listOf(UserRepoDTO(id, ""))))
        repository.getRxUserRepositories(login)
        verify(gitHubApi).listRepos(login)
    }
}