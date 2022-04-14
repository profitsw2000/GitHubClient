package ru.profitsw2000.githubclient.ui.screens.main

import ru.profitsw2000.githubclient.domain.ClientApiUseCase
import ru.profitsw2000.githubclient.domain.entities.UserProfile
import ru.profitsw2000.githubclient.ui.ViewModel
import ru.profitsw2000.githubclient.utils.Publisher


private const val ERROR_EMPTY_USERS_LIST = 1

class MainViewModel (private val clientApiUseCase: ClientApiUseCase) : ViewModel {
    override val showProgress: Publisher<Boolean> = Publisher()
    override val getUserProfileList: Publisher<List<UserProfile>> = Publisher()
    override val errorCode: Publisher<Int?> = Publisher()

    override fun onLoadUserList() {
        showProgress.post(true)
        clientApiUseCase.getUserList { result ->
            if (result != null) {
                getUserProfileList.post(result)
            } else {
                errorCode.post(ERROR_EMPTY_USERS_LIST)
            }
        }
        showProgress.post(false)
    }
}