package ru.profitsw2000.githubclient.ui.screens.details

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import ru.profitsw2000.githubclient.domain.ClientApiUseCase
import ru.profitsw2000.githubclient.domain.entities.UserDetails
import ru.profitsw2000.githubclient.domain.entities.UserRepo
import ru.profitsw2000.githubclient.utils.Publisher

private const val ERROR_EMPTY_USER_DESCRIPTION = 1
private const val ERROR_EMPTY_USER_REPO_LIST = 2

class DetailsViewModel (private val clientApiUseCase: ClientApiUseCase) : ViewModel {
    override val showProgress: Publisher<Boolean> = Publisher()
    override val getUserRepoList: Publisher<List<UserRepo>> = Publisher()
    override val getUserInfo: Publisher<UserDetails> = Publisher()
    override val errorCode: Publisher<Int?> = Publisher()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onLoadUserInfo(login: String) {
        showProgress.post(true)
        compositeDisposable.add(
            clientApiUseCase.getRxUserInfo(login)
                .subscribeBy {
                    showProgress.post(false)
                    getUserInfo.post(it)
                }
        )
    }

    override fun onLoadUserRepoList() {
        TODO("Not yet implemented")
    }
}