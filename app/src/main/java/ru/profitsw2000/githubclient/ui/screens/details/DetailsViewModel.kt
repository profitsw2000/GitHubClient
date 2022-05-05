package ru.profitsw2000.githubclient.ui.screens.details

import android.content.Context
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import ru.profitsw2000.githubclient.app
import ru.profitsw2000.githubclient.data.web.WebRepositoryImpl
import ru.profitsw2000.githubclient.data.web.entities.UserDetailsDTO
import ru.profitsw2000.githubclient.data.web.entities.UserRepoDTO
import ru.profitsw2000.githubclient.utils.Publisher

private const val ERROR_EMPTY_USER_DESCRIPTION = 1
private const val ERROR_EMPTY_USER_REPO_LIST = 2

class DetailsViewModel(private val repositoryUseCase: WebRepositoryImpl, context: Context) : ViewModel, KoinComponent {
    override val showProgress = context.app.appComponent.getShowProgress()
    override val getUserRepoList = context.app.appComponent.getUserRepoList()
    override val getUserInfo = context.app.appComponent.getUserInfo()
    override val errorCode = context.app.appComponent.getErrorCode()

    private val compositeDisposable = context.app.appComponent.getCompositeDisposable()

    override fun onLoadUserInfo(login: String) {
        var inProgress1 = true
        var inProgress2 = true

        showProgress.post(inProgress1 && inProgress2)
        val disposable1 =
            repositoryUseCase.getRxUserInfo(login)
                .subscribeBy({
                    showProgress.post(false)
                    errorCode.post(ERROR_EMPTY_USER_DESCRIPTION)
                }, {
                    inProgress1 = false
                    showProgress.post(inProgress1 && inProgress2)
                    getUserInfo.post(it)
                })

        val disposable2 =
            repositoryUseCase.getRxUserRepositories(login)
                .subscribeBy({
                    showProgress.post(false)
                    errorCode.post(ERROR_EMPTY_USER_REPO_LIST)
                }, {
                    inProgress2 = false
                    showProgress.post(inProgress1 && inProgress2)
                    getUserRepoList.post(it)
                })

        compositeDisposable.addAll(disposable1)
        compositeDisposable.addAll(disposable2)
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}