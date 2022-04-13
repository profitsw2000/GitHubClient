package ru.profitsw2000.githubclient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.profitsw2000.githubclient.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    //private var adapter: UserListAdapter? = null

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
        val adapter = UserListAdapter()
        adapter.setData(userList)
        binding.userListRecyclerview.adapter = adapter
        //adapter?.setData(userList)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}