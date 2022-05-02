package ru.profitsw2000.githubclient.ui.screens.main

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import ru.profitsw2000.githubclient.data.local.entities.UserProfile
import ru.profitsw2000.githubclient.data.web.WebRepositoryImpl
import ru.profitsw2000.githubclient.data.web.entities.UserDTO
import ru.profitsw2000.githubclient.data.web.entities.UserDetailsDTO
import ru.profitsw2000.githubclient.ui.ViewModel
import ru.profitsw2000.githubclient.utils.Publisher


private const val ERROR_EMPTY_USERS_LIST = 1
private const val ERROR_USER_NOT_FOUND = 2

class MainViewModel(private val repositoryUseCase: WebRepositoryImpl) : ViewModel, KoinComponent {
    override val showProgress: Publisher<Boolean> by inject(named("showProgress"))
    override val getUserProfileList: Publisher<List<UserProfile>> by inject(named("getUserProfileList"))
    override val getUserList: Publisher<List<UserDTO>> by inject(named("getUserList"))
    override val getUser: Publisher<UserDetailsDTO> by inject(named("getUser"))
    override val errorCode: Publisher<Int?> by inject(named("errorCode"))

    private val compositeDisposable: CompositeDisposable by inject()

    override fun onLoadUserList() {
/*        showProgress.post(true)
        val result = repositoryUseCase.getUserList()
        if (result != null) {
            getUserProfileList.post(result)
        } else {
            errorCode.post(ERROR_EMPTY_USERS_LIST)
        }
        showProgress.post(false)*/
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

    override fun onLoadRxUser(login: String) {
        showProgress.post(true)
        compositeDisposable.add(
            repositoryUseCase.getRxUserInfo(login)
                .subscribeBy ({
                    showProgress.post(false)
                    errorCode.post(ERROR_USER_NOT_FOUND)
                }, {
                    showProgress.post(false)
                    getUser.post(it)
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}