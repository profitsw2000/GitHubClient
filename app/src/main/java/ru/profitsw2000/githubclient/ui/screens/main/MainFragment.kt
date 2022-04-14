package ru.profitsw2000.githubclient.ui.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.profitsw2000.githubclient.utils.OnItemClickListener
import ru.profitsw2000.githubclient.R
import ru.profitsw2000.githubclient.domain.entities.UserProfile
import ru.profitsw2000.githubclient.databinding.FragmentMainBinding
import ru.profitsw2000.githubclient.ui.adapters.UserListAdapter
import ru.profitsw2000.githubclient.ui.screens.details.UserInfoFragment

private const val BUNDLE_EXTRA = "user profile"

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userList: MutableList<UserProfile> =
            mutableListOf(
                UserProfile(0, "Piccolo", "Middle Developer", "San-Francisco","", mutableListOf("Repo1", "Repo2")),
                UserProfile(1, "MorCam", "Senior Developer", "Chickago","", mutableListOf("Repo1", "Repo2")),
                UserProfile(2, "AscorImpact", "Senior Developer", "Deli","", mutableListOf("Repo1", "Repo2")),
                UserProfile(3, "FanReid", "Middle Developer", "Seoul","", mutableListOf("Repo1", "Repo2")),
                UserProfile(4, "ZoccoFear", "Junior Developer", "Bombei","", mutableListOf("Repo1", "Repo2")),
                UserProfile(5, "Caroq", "Senior Developer", "Boston","", mutableListOf("Repo1", "Repo2")),
                UserProfile(6, "Wayscorer", "Senior Developer", "Paris","", mutableListOf("Repo1", "Repo2")),
                UserProfile(7, "Irwitch", "Middle Developer", "Berlin","", mutableListOf("Repo1", "Repo2")),
                UserProfile(8, "Charger001", "Junior Developer", "Melbourn","", mutableListOf("Repo1", "Repo2"))
            )
        val adapter = UserListAdapter(object : OnItemClickListener {
            override fun onItemClick(userProfile: UserProfile) {
                val bundle = Bundle().apply {
                    putParcelable(BUNDLE_EXTRA, userProfile)
                }
                openFragment(UserInfoFragment.newInstance(bundle))
            }
        })
        adapter.setData(userList)
        binding.userListRecyclerview.adapter = adapter
    }

    private fun openFragment(fragment: Fragment) {

        val manager = activity?.supportFragmentManager

        manager?.let {
            manager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}