package ru.profitsw2000.githubclient.ui.screens.main

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.ext.android.inject
import ru.profitsw2000.githubclient.utils.OnItemClickListener
import ru.profitsw2000.githubclient.R
import ru.profitsw2000.githubclient.app
import ru.profitsw2000.githubclient.data.web.WebRepositoryImpl
import ru.profitsw2000.githubclient.data.web.entities.UserDTO
import ru.profitsw2000.githubclient.databinding.FragmentMainBinding
import ru.profitsw2000.githubclient.domain.RepositoryUseCase
import ru.profitsw2000.githubclient.ui.ViewModel
import javax.inject.Inject

private const val ERROR_EMPTY_USERS_LIST = 1
private const val ERROR_USER_NOT_FOUND = 2

class MainFragment : Fragment() {

    @Inject
    lateinit var repositoryUseCase: RepositoryUseCase
    //private val userList = context?.app?.appComponent?.getUserList()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var viewModel: ViewModel? = null
    private val handler: Handler by lazy { Handler(Looper.getMainLooper()) }
    private var adapter: UserListAdapter? = null
    private val controller by lazy { activity as Controller }
    private val userList: MutableList<UserDTO> = mutableListOf()
    private var userPressedFlag: Boolean = false
    private var searchButtonPressedFlag: Boolean = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity !is Controller) {
            throw IllegalStateException("Activity должна наследоваться от Controller")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        adapter = UserListAdapter(object : OnItemClickListener {
            override fun onItemClick(user: UserDTO) {
                controller.openUserDetails(user.login)
                userPressedFlag = true
            }
        })
        context?.app?.appComponent?.injectMainFragment(this)

        viewModel = restoreViewModel()

        viewModelSubscribe()

        viewModel?.onLoadRxUserList()
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
                ERROR_EMPTY_USERS_LIST -> showDialog(
                    getString(R.string.dialog_empty_users_list_error_title),
                    getString(R.string.dialog_empty_users_list_error_text)
                )
                ERROR_USER_NOT_FOUND -> {
                    with(binding) {
                        userListRecyclerview.visibility = View.GONE
                        userNotFoundTextView.visibility = View.VISIBLE
                    }
                }
                else -> {}
            }
        }

        viewModel?.getUserList?.subscribe(handler) {
            if (it != null) {
                userList.addAll(userList.size, it)
                adapter?.setData(userList)
            }
        }

        viewModel?.getUserProfileList?.subscribe(handler) {
            if(it != null) {
                for (user in it) {
                    userList.add(UserDTO(user.userName, user.id, user.avatarUrl, user.userInfo))
                }
                adapter?.setData(userList)
            }
        }

        viewModel?.getUser?.subscribe(handler) {
            if (it != null) {
                userList.clear()
                userList.add(UserDTO(it.login, 999, it.avatar_url, "User"))
                binding.userListRecyclerview.removeAllViews()
                adapter?.setData(userList)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.userListRecyclerview.adapter = adapter
        binding.userListRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!binding.userListRecyclerview.canScrollVertically(1) && userList.size > 4){
                    if (!userList.isNullOrEmpty()) {
                        viewModel?.onLoadRxUserList(userList.last().id)
                    }
                }
            }
        })

        binding.searchUserInputLayout.setEndIconOnClickListener {
            viewModel?.onLoadRxUser(binding.searchUserInputEditText.text.toString())
            searchButtonPressedFlag = true
        }

        binding.searchUserInputEditText.addTextChangedListener {
            if (it.toString().isNullOrEmpty()
                && !userPressedFlag
                && searchButtonPressedFlag) {
                searchButtonPressedFlag = false
                userList.clear()
                binding.userListRecyclerview.removeAllViews()
                viewModel?.onLoadRxUserList()
            }
            userPressedFlag = false
        }

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
        viewModel?.onCleared()
    }

    private fun showProgress() {
        with(binding) {
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun hideProgress() {
        with(binding) {
            progressBar.visibility = View.GONE
            userListRecyclerview.visibility = View.VISIBLE
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
    }

    private fun restoreViewModel(): MainViewModel {
        return MainViewModel(repositoryUseCase as WebRepositoryImpl)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    interface Controller {
        fun openUserDetails(login: String)
    }
}

