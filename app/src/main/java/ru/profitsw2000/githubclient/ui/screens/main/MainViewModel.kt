package ru.profitsw2000.githubclient.ui.screens.main

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.inject
import ru.profitsw2000.githubclient.data.local.entities.UserProfile
import ru.profitsw2000.githubclient.data.web.WebRepositoryImpl
import ru.profitsw2000.githubclient.data.web.entities.UserDTO
import ru.profitsw2000.githubclient.ui.ViewModel
import ru.profitsw2000.githubclient.utils.Publisher


private const val ERROR_EMPTY_USERS_LIST = 1

class MainViewModel(private val repositoryUseCase: WebRepositoryImpl) : ViewModel, KoinComponent {
    override val showProgress: Publisher<Boolean> = Publisher() //by inject(named("showProgress"))
    //override val getUserProfileList: Publisher<List<UserProfile>> by inject(named("getUserProfileList"))
    override val getUserList: Publisher<List<UserDTO>> = Publisher() //by inject(named("getUserList"))
    override val errorCode: Publisher<Int?>  = Publisher() //by inject(named("errorCode"))

    private val compositeDisposable = CompositeDisposable() // by inject()

    override fun onLoadUserList() {

    }

    override fun onLoadRxUserList() {
        showProgress.post(true)
        compositeDisposable.add(
            repositoryUseCase.getRxUserList()
                .subscribeBy ({
                    showProgress.post(false)
                    errorCode.post(ERROR_EMPTY_USERS_LIST)
                }, {
                    showProgress.post(false)
                    getUserList.post(it)
                })
        )
    }

    override fun onLoadRxUserList(fromId: Int) {
        showProgress.post(true)
        compositeDisposable.add(
            repositoryUseCase.getRxUserList(fromId)
                .subscribeBy {
                    showProgress.post(false)
                    getUserList.post(it)
                }
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}