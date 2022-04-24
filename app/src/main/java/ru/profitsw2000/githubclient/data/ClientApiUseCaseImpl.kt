package ru.profitsw2000.githubclient.data

import android.os.Handler
import androidx.annotation.MainThread
import io.reactivex.rxjava3.core.Single
import ru.profitsw2000.githubclient.domain.ClientApi
import ru.profitsw2000.githubclient.domain.ClientApiUseCase
import ru.profitsw2000.githubclient.domain.entities.User
import ru.profitsw2000.githubclient.domain.entities.UserDetails
import ru.profitsw2000.githubclient.domain.entities.UserProfile
import ru.profitsw2000.githubclient.domain.entities.UserRepo
=======
import ru.profitsw2000.githubclient.domain.ClientApi
import ru.profitsw2000.githubclient.domain.ClientApiUseCase
import ru.profitsw2000.githubclient.domain.entities.UserProfile


class ClientApiUseCaseImpl(
    private val api: ClientApi,
    private val uiHandler: Handler
    ) : ClientApiUseCase {
    override fun getUserList(@MainThread callback: (List<UserProfile>?) -> Unit) {
        Thread {
            val result = api.getUserList()
            uiHandler.post {
                callback(result)
            }
        }.start()
    }


    override fun getRxUserList(): Single<List<User>> {
        return api.getRxUserList()
    }

    override fun getRxUserInfo(login: String): Single<UserDetails> {
        return api.getRxUserInfo(login)
    }

    override fun getRxUserRepositories(login: String): Single<List<UserRepo>> {
        return api.getRxUserRepositories(login)
    }
=======

}