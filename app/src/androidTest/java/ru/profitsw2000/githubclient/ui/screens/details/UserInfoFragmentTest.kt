package ru.profitsw2000.githubclient.ui.screens.details

import android.app.PendingIntent.getActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.espresso.Espresso.onView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.profitsw2000.githubclient.R
import ru.profitsw2000.githubclient.data.web.entities.UserDetailsDTO

@RunWith(AndroidJUnit4::class)
class UserInfoFragmentTest{
    private lateinit var scenario: FragmentScenario<UserInfoFragment>
    val fragmentArgs = bundleOf("user profile" to "AscorImpact")

    @Before
    fun setup() {
        scenario = launchFragmentInContainer<UserInfoFragment>(fragmentArgs, R.style.Theme_GitHubClient)
    }

    @Test
    fun testBundle() {
        scenario.moveToState(Lifecycle.State.RESUMED)

        val assertion = matches(withText("AscorImpact"))
        val assertion1 = matches(withText("Deli"))
        onView(withId(R.id.login_text_view)).check(assertion)
        onView(withId(R.id.person_name_text_view)).check(assertion)
        onView(withId(R.id.person_city_text_view)).check(assertion1)
    }

    @Test
    fun fragment_setInfoTest() {
        scenario.onFragment { fragment ->
            fragment.setUserInfo(UserDetailsDTO(
                "Ivan",
                "https://avatars.githubusercontent.com/u/84991333?s=400&v=4",
                "Rou",
                "Beograd"
            ))
        }

        val assertionLogin = matches(withText("Ivan"))
        val assertionName = matches(withText("Rou"))
        val assertionCity = matches(withText("Beograd"))

        onView(withId(R.id.login_text_view)).check(assertionLogin)
        onView(withId(R.id.person_name_text_view)).check(assertionName)
        onView(withId(R.id.person_city_text_view)).check(assertionCity)
    }

    @Test
    fun testUserRepositoryList() {
        scenario.moveToState(Lifecycle.State.RESUMED)

        onView(withId(R.id.repositories_list_recyclerview))
            .perform(RecyclerViewActions.scrollTo<UserRepositoriesAdapter.ViewHolder>(
                hasDescendant(withText("Repo20"))
            ))

        onView(withId(R.id.repositories_list_recyclerview))
            .perform(RecyclerViewActions.actionOnItem<UserRepositoriesAdapter.ViewHolder>(
                hasDescendant(withText("Repo15")),
                click()
            ))
    }

    @After
    fun close() {
        scenario.close()
    }
}