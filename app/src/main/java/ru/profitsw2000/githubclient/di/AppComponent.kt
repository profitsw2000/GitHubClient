package ru.profitsw2000.githubclient.di

import dagger.Component
import ru.profitsw2000.githubclient.data.web.entities.UserDTO
import ru.profitsw2000.githubclient.ui.screens.details.UserInfoFragment
import ru.profitsw2000.githubclient.ui.screens.main.MainFragment
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [WebModule::class])
interface AppComponent {
    fun injectMainFragment(mainFragment: MainFragment)
    fun injectUserInfoFragment(userInfoFragment: UserInfoFragment)
    @Named("userList")
    fun getUserList(): MutableList<UserDTO>
}