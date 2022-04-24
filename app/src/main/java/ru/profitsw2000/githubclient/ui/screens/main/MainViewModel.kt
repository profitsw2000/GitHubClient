package ru.profitsw2000.githubclient.ui.screens.main

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import ru.profitsw2000.githubclient.domain.ClientApiUseCase
import ru.profitsw2000.githubclient.domain.entities.User
=======
import ru.profitsw2000.githubclient.domain.ClientApiUseCase
import ru.profitsw2000.githubclient.domain.entities.UserProfile
import ru.profitsw2000.githubclient.ui.ViewModel
import ru.profitsw2000.githubclient.utils.Publisher


private const val ERROR_EMPTY_USERS_LIST = 1

class MainViewModel (private val clientApiUseCase: ClientApiUseCase) : ViewModel {
    override val showProgress: Publisher<Boolean> = Publisher()
    override val getUserProfileList: Publisher<List<UserProfile>> = Publisher()
    override val getUserList: Publisher<List<User>> = Publisher()
    override val errorCode: Publisher<Int?> = Publisher()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

=======
    override val errorCode: Publisher<Int?> = Publisher()

    override fun onLoadUserList() {
        showProgress.post(true)
        clientApiUseCase.getUserList { result ->
            if (result != null) {
                getUserProfileList.post(result)
            } else {
                errorCode.post(ERROR_EMPTY_USERS_LIST)
            }
            showProgress.post(false)
        }
    }

    override fun onLoadRxUserList() {
        showProgress.post(true)
        compositeDisposable.add(
            clientApiUseCase.getRxUserList()
                .subscribeBy {
                    showProgress.post(false)
                    getUserList.post(it)
                }
        )
    }
=======
}