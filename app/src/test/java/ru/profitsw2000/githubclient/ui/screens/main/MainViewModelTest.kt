package ru.profitsw2000.githubclient.ui.screens.main

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import ru.profitsw2000.githubclient.data.web.WebRepositoryImpl

internal class MainViewModelTest {

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
        mainViewModel.onLoadRxUserList()
        verify(repository, times(1)).getRxUserList()
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