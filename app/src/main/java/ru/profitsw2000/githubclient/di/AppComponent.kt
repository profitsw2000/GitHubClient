package ru.profitsw2000.githubclient.di

import dagger.Component
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.profitsw2000.githubclient.data.local.entities.UserProfile
import ru.profitsw2000.githubclient.data.web.entities.UserDTO
import ru.profitsw2000.githubclient.data.web.entities.UserDetailsDTO
import ru.profitsw2000.githubclient.data.web.entities.UserRepoDTO
import ru.profitsw2000.githubclient.ui.screens.details.UserInfoFragment
import ru.profitsw2000.githubclient.ui.screens.main.MainFragment
import ru.profitsw2000.githubclient.ui.screens.main.MainViewModel
import ru.profitsw2000.githubclient.utils.Publisher
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [WebModule::class, VMModule::class])
interface AppComponent {
    fun injectMainFragment(mainFragment: MainFragment)
    fun injectUserInfoFragment(userInfoFragment: UserInfoFragment)
    @Named("userList")
    fun getUserList(): MutableList<UserDTO>
    fun injectMaiViewModel(mainViewModel: MainViewModel)

    @Named("showProgress")
    fun getShowProgress(): Publisher<Boolean>

    @Named("getUserProfileList")
    fun getUserProfileList(): Publisher<List<UserProfile>>

    @Named("getUserDTOList")
    fun getUserDTOList(): Publisher<List<UserDTO>>

    @Named("getUser")
    fun getUser(): Publisher<UserDetailsDTO>

    @Named("errorCode")
    fun getErrorCode(): Publisher<Int>

    @Named("getUserRepoList")
    fun getUserRepoList(): Publisher<List<UserRepoDTO>>

    @Named("getUserInfo")
    fun getUserInfo(): Publisher<UserDetailsDTO>

    @Named("compositeDisposable")
    fun getCompositeDisposable(): CompositeDisposable
}