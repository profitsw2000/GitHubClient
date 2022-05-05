package ru.profitsw2000.githubclient.di

import dagger.Component
import ru.profitsw2000.githubclient.ui.screens.main.MainFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [WebModule::class])
interface AppComponent {
    fun injectMainFragment(mainFragment: MainFragment)
}