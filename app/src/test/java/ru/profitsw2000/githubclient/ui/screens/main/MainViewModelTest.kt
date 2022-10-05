package ru.profitsw2000.githubclient.ui.screens.main

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.profitsw2000.githubclient.data.web.WebRepositoryImpl
import ru.profitsw2000.githubclient.data.web.entities.UserDTO

internal class MainViewModelTest {

    private val id = 5
    private lateinit var mainViewModel: MainViewModel
    @Mock
    private lateinit var repository: WebRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainViewModel = MainViewModel(repository)
    }

    @Test  //проверка вызова метода getRxUserList
    fun webRepository_getRxUserList_Test() {
        Mockito.`when`(repository.getRxUserList()).thenReturn(Single.just(listOf(UserDTO("",0,"",""))))
        mainViewModel.onLoadRxUserList()
        verify(repository, times(1)).getRxUserList()
    }

    @Test  //проверка вызова метода getRxUserList(fromId)
    fun webRepository_getRxUserListfromId_Test() {
        Mockito.`when`(repository.getRxUserList(id)).thenReturn(Single.just(listOf(UserDTO("",0,"",""))))
        mainViewModel.onLoadRxUserList(id)
        verify(repository, times(1)).getRxUserList(id)
    }

/*    @Test   //проверка вызова обратной функции для showProgress
    fun showProgress_CallBack_Invoked() {
        mainViewModel.showProgress.subscribe(handler, booleanCallBack)
        mainViewModel.showProgress.post(true)
        verify(booleanCallBack, times(1))
    }*/
}