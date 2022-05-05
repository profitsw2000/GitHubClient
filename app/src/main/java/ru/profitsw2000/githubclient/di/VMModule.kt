package ru.profitsw2000.githubclient.di

import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.profitsw2000.githubclient.data.local.entities.UserProfile
import ru.profitsw2000.githubclient.data.web.entities.UserDTO
import ru.profitsw2000.githubclient.data.web.entities.UserDetailsDTO
import ru.profitsw2000.githubclient.data.web.entities.UserRepoDTO
import ru.profitsw2000.githubclient.utils.Publisher
import javax.inject.Named

@Module
class VMModule(val context: Context) {
    @Provides
    @Named("showProgress")
    fun getShowProgress(): Publisher<Boolean> {
        return Publisher()
    }

    @Provides
    @Named("getUserProfileList")
    fun getUserProfileList(): Publisher<List<UserProfile>> {
        return Publisher()
    }

    @Provides
    @Named("getUserDTOList")
    fun getUserDTOList(): Publisher<List<UserDTO>> {
        return Publisher()
    }

    @Provides
    @Named("getUser")
    fun getUser(): Publisher<UserDetailsDTO> {
        return Publisher()
    }

    @Provides
    @Named("errorCode")
    fun getErrorCode(): Publisher<Int> {
        return Publisher()
    }

    @Provides
    @Named("getUserRepoList")
    fun getUserRepoList(): Publisher<List<UserRepoDTO>> {
        return Publisher()
    }

    @Provides
    @Named("getUserInfo")
    fun getUserInfo(): Publisher<UserDetailsDTO> {
        return Publisher()
    }

    @Provides
    @Named("compositeDisposable")
    fun getCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}