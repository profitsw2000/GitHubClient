package ru.profitsw2000.githubclient.data

import android.os.Handler
import androidx.annotation.MainThread
import io.reactivex.rxjava3.core.Single
import ru.profitsw2000.githubclient.domain.ClientApi
import ru.profitsw2000.githubclient.domain.ClientApiUseCase
import ru.profitsw2000.githubclient.domain.entities.User
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
}