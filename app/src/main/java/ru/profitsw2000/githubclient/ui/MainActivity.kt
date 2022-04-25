package ru.profitsw2000.githubclient.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import ru.profitsw2000.githubclient.R
import ru.profitsw2000.githubclient.ui.screens.details.UserInfoFragment
import ru.profitsw2000.githubclient.ui.screens.main.MainFragment

private const val BUNDLE_EXTRA = "user profile"

class MainActivity : AppCompatActivity(), MainFragment.Controller {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.apply {
                beginTransaction()
                    .replace(R.id.fragment_container, MainFragment.newInstance())
                    .commitAllowingStateLoss()
            }
        }
    }

    override fun openUserDetails(login: String) {
        val bundle = Bundle().apply {
            putString(BUNDLE_EXTRA, login)
        }
        val manager = supportFragmentManager

        manager?.let {
            manager.beginTransaction()
                .replace(R.id.fragment_container, UserInfoFragment.newInstance(bundle))
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }
}