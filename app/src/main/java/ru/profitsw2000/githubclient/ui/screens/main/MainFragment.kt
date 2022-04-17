package ru.profitsw2000.githubclient.ui.screens.main

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.profitsw2000.githubclient.utils.OnItemClickListener
import ru.profitsw2000.githubclient.R
import ru.profitsw2000.githubclient.app
import ru.profitsw2000.githubclient.domain.entities.UserProfile
import ru.profitsw2000.githubclient.databinding.FragmentMainBinding
import ru.profitsw2000.githubclient.ui.ViewModel
import ru.profitsw2000.githubclient.ui.screens.details.UserInfoFragment

private const val BUNDLE_EXTRA = "user profile"
private const val ERROR_EMPTY_USERS_LIST = 1

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var viewModel: ViewModel? = null
    private val handler: Handler by lazy { Handler(Looper.getMainLooper()) }
    private var adapter: UserListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        adapter = UserListAdapter(object : OnItemClickListener {
            override fun onItemClick(userProfile: UserProfile) {
                val bundle = Bundle().apply {
                    putParcelable(BUNDLE_EXTRA, userProfile)
                }
                openFragment(UserInfoFragment.newInstance(bundle))
            }
        })

        viewModel = restoreViewModel()
        viewModel?.showProgress?.subscribe(handler) {
            if (it == true) {
                showProgress()
            } else {
                hideProgress()
            }
        }

        viewModel?.errorCode?.subscribe(handler) {
            when(it){
                ERROR_EMPTY_USERS_LIST -> showDialog(getString(R.string.dialog_empty_users_list_error_title),
                    getString(R.string.dialog_empty_users_list_error_text))
                else -> {}
            }
        }

        viewModel?.getUserProfileList?.subscribe(handler) {
            if (it != null) {adapter?.setData(it)}
        }

        viewModel?.getUserList?.subscribe(handler) {
            val users: MutableList<UserProfile> = mutableListOf()

            if (it != null) {
                for (user in it){
                    users.add(UserProfile(user.id,
                                        user.login,
                                        user.avatar_url,
                                    "Moscow",
                                        user.avatar_url,
                                        mutableListOf("Repo1", "Repo2")))
                }
                adapter?.setData(users)
            }
        }

        viewModel?.onLoadRxUserList()
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
        binding.userListRecyclerview.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.showProgress?.unsubscribeAll()
        viewModel?.getUserProfileList?.unsubscribeAll()
        viewModel?.getUserList?.unsubscribeAll()
        viewModel?.errorCode?.unsubscribeAll()
    }

    private fun showProgress() {
        with(binding){
            progressBar.visibility = View.VISIBLE
            userListRecyclerview.visibility = View.GONE
        }
    }

    private fun hideProgress() {
        with(binding){
            progressBar.visibility = View.GONE
            userListRecyclerview.visibility = View.VISIBLE
        }
    }

    private fun showDialog(title: String, message: String) {
        context.let {
            AlertDialog.Builder(it)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(getString(R.string.dialog_button_ok_text)){
                        dialog, _ -> dialog.dismiss() }
                .create()
                .show()
        }
    }

    private fun restoreViewModel(): MainViewModel {
        return MainViewModel(context?.app!!.clientApiUseCase)
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