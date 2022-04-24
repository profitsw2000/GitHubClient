package ru.profitsw2000.githubclient.ui.screens.details

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
=======
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.api.load
import ru.profitsw2000.githubclient.R
import ru.profitsw2000.githubclient.app
import ru.profitsw2000.githubclient.databinding.FragmentUserInfoBinding
import ru.profitsw2000.githubclient.domain.entities.UserProfile
import ru.profitsw2000.githubclient.ui.ViewModel

private const val BUNDLE_EXTRA = "user profile"
private const val ERROR_EMPTY_USER_DESCRIPTION = 1
private const val ERROR_EMPTY_USER_REPO_LIST = 2
=======
import ru.profitsw2000.githubclient.databinding.FragmentUserInfoBinding
import ru.profitsw2000.githubclient.domain.entities.UserProfile

private const val BUNDLE_EXTRA = "user profile"

class UserInfoFragment : Fragment() {

    private var _binding: FragmentUserInfoBinding? = null
    private val binding get() = _binding!!
    private var adapter: UserRepositoriesAdapter? = null
    private val handler: Handler by lazy { Handler(Looper.getMainLooper()) }
    private var viewModel: ru.profitsw2000.githubclient.ui.screens.details.ViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userLogin = arguments?.getString(BUNDLE_EXTRA)
        adapter = UserRepositoriesAdapter()

        viewModel = DetailsViewModel(context?.app!!.clientApiUseCase)

        viewModelSubscribe()

        viewModel?.onLoadUserInfo(userLogin!!)
    }

    private fun viewModelSubscribe() {
        viewModel?.showProgress?.subscribe(handler) {
            if (it == true) {
                showProgress()
            } else {
                hideProgress()
            }
        }

        viewModel?.errorCode?.subscribe(handler) {
            when (it) {
                ERROR_EMPTY_USER_DESCRIPTION -> showDialog(
                    getString(R.string.dialog_empty_users_list_error_title),
                    getString(R.string.dialog_user_info_load_error_text)
                )
                ERROR_EMPTY_USER_REPO_LIST -> showDialog(
                    getString(R.string.dialog_empty_users_list_error_title),
                    getString(R.string.dialog_user_repos_load_error_text)
                )
                else -> {}
            }
        }

        viewModel?.getUserInfo?.subscribe(handler) {
            if (it != null) {
                with(binding) {
                    personPhotoImageView.load(it.avatar_url)
                    loginTextView.setText(it.login)
                    personNameTextView.setText(it.name)
                    personCityTextView.setText(it.location)
                }
            }
        }

        viewModel?.getUserRepoList?.subscribe(handler) {
            if (it != null) {
                adapter?.setData(it)
            }
        }
=======

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = UserRepositoriesAdapter()
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
        binding.repositoriesListRecyclerview.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.showProgress?.unsubscribeAll()
        viewModel?.getUserInfo?.unsubscribeAll()
        viewModel?.getUserRepoList?.unsubscribeAll()
        viewModel?.errorCode?.unsubscribeAll()
    }

    private fun showProgress() {
        with(binding) {
            progressBar.visibility = View.VISIBLE
            userInfoCardView.visibility = View.GONE
            userRepositoryListCardView.visibility = View.GONE
        }
    }

    private fun hideProgress() {
        with(binding) {
            progressBar.visibility = View.GONE
            userInfoCardView.visibility = View.VISIBLE
            userRepositoryListCardView.visibility = View.VISIBLE
        }
    }

    private fun showDialog(title: String, message: String) {
        context.let {
            AlertDialog.Builder(it)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(getString(R.string.dialog_button_ok_text)) { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
        }
=======
        val userProfile = arguments?.getParcelable<UserProfile>(BUNDLE_EXTRA)

        with(binding) {
            personNameTextView.text = userProfile?.userName
            aboutPersonTextView.text = userProfile?.userInfo
            personCityTextView.text = userProfile?.userCity
            repositoriesListRecyclerview.adapter = adapter
        }
        adapter?.setData(userProfile?.userRepositories!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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