package ru.profitsw2000.githubclient.ui.screens.main

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.rxjava3.core.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.profitsw2000.githubclient.App
import ru.profitsw2000.githubclient.data.web.WebRepositoryImpl
import ru.profitsw2000.githubclient.data.web.entities.UserDTO
import ru.profitsw2000.githubclient.di.appModule

internal class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel
    @Mock
    private lateinit var repository: WebRepositoryImpl
    private val id = 5

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(repository.getRxUserList()).thenReturn(Single.just(listOf(UserDTO("",0,"",""))))
        Mockito.`when`(repository.getRxUserList(id)).thenReturn(Single.just(listOf(UserDTO("",0,"",""))))
        mainViewModel = MainViewModel(repository)
    }

    @Test  //проверка вызова метода getRxUserList
    fun webRepository_getRxUserList_Test() {
        mainViewModel.onLoadRxUserList()
        verify(repository, times(1)).getRxUserList()
    }

    @Test  //проверка вызова метода getRxUserList(fromId)
    fun webRepository_getRxUserListfromId_Test() {
        mainViewModel.onLoadRxUserList(id)
        verify(repository, times(1)).getRxUserList(id)
    }

/*    @Test
    fun getShowProgress() {
    }

    @Test
    fun getGetUserList() {
    }

    @Test
    fun getErrorCode() {
    }

    @Test
    fun onLoadRxUserList() {
    }

    @Test
    fun testOnLoadRxUserList() {
    }*/
}