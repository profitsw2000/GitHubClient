package ru.profitsw2000.githubclient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ru.profitsw2000.githubclient.databinding.FragmentMainBinding

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
        val userList = listOf(UserProfile(0, "Piccolo", "Middle Developer", "San-Francisco"),
            UserProfile(0, "MorCam", "Senior Developer", "Chickago"),
            UserProfile(0, "AscorImpact", "Senior Developer", "Deli"),
            UserProfile(0, "FanReid", "Middle Developer", "Seoul"),
            UserProfile(0, "ZoccoFear", "Junior Developer", "Bombei"),
            UserProfile(0, "MorCam", "Senior Developer", "Chickago"),
            UserProfile(0, "AscorImpact", "Senior Developer", "Deli"),
            UserProfile(0, "FanReid", "Middle Developer", "Seoul"),
            UserProfile(0, "ZoccoFear", "Junior Developer", "Bombei"))
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