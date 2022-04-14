package ru.profitsw2000.githubclient.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.profitsw2000.githubclient.databinding.FragmentMainBinding
import ru.profitsw2000.githubclient.databinding.FragmentUserInfoBinding
import ru.profitsw2000.githubclient.domain.entities.UserProfile

private const val BUNDLE_EXTRA = "user profile"

class UserInfoFragment : Fragment() {

    private var _binding: FragmentUserInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userProfile = arguments?.getParcelable<UserProfile>(BUNDLE_EXTRA)
        with(binding) {
            personNameTextView.text = userProfile?.userName
            aboutPersonTextView.text = userProfile?.userInfo
            personCityTextView.text = userProfile?.userCity
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): UserInfoFragment {
            val fragment = UserInfoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}