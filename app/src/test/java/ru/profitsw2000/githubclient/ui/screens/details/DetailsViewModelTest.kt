package ru.profitsw2000.githubclient.ui.screens.details


import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.profitsw2000.githubclient.data.local.MockRepositoryImpl
import ru.profitsw2000.githubclient.data.web.WebRepositoryImpl
import ru.profitsw2000.githubclient.data.web.entities.UserDetailsDTO
import ru.profitsw2000.githubclient.data.web.entities.UserRepoDTO


internal class DetailsViewModelTest {

    private val login = "login"
    private lateinit var detailsViewModel: DetailsViewModel
    @Mock
    private lateinit var repository: WebRepositoryImpl
    private lateinit var mockRepository: MockRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        detailsViewModel = DetailsViewModel(repository, mockRepository)
    }

    @Test  //проверка вызова метода getRxUserInfo и getRxUserRepositories
    fun webRepository_getRxUserInfoAndRepositories_Test() {
        Mockito.`when`(repository.getRxUserInfo(login)).thenReturn(Single.just(UserDetailsDTO("","","","")))
        Mockito.`when`(repository.getRxUserRepositories(login)).thenReturn(Single.just(listOf(UserRepoDTO(0,""))))
        detailsViewModel.onLoadUserInfo(login)
        verify(repository, times(1)).getRxUserInfo(login)
        verify(repository, times(1)).getRxUserRepositories(login)
    }
}