package ru.profitsw2000.githubclient.ui.screens.details

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
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

    @After
    fun close() {
        scenario.close()
    }
}