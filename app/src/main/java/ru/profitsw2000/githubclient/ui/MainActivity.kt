package ru.profitsw2000.githubclient.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import ru.profitsw2000.githubclient.R
import ru.profitsw2000.githubclient.ui.screens.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFragment(MainFragment.newInstance())
    }

    private fun openFragment(fragment: Fragment) {
        if(supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
            supportFragmentManager.apply {
                beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commitAllowingStateLoss()
            }
        }

    }
}