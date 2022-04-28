package ru.profitsw2000.githubclient.ui.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import ru.profitsw2000.githubclient.data.web.entities.UserDTO
import ru.profitsw2000.githubclient.utils.OnItemClickListener
import ru.profitsw2000.githubclient.databinding.UserListItemViewBinding

class UserListAdapter(val onItemClickListener: OnItemClickListener): RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private var data: List<UserDTO> = arrayListOf()
    private lateinit var binding: UserListItemViewBinding

    fun setData (data: List<UserDTO>) {
=======
import ru.profitsw2000.githubclient.utils.OnItemClickListener
import ru.profitsw2000.githubclient.domain.entities.UserProfile
import ru.profitsw2000.githubclient.databinding.UserListItemViewBinding
import ru.profitsw2000.githubclient.domain.entities.User

class UserListAdapter(val onItemClickListener: OnItemClickListener): RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private var data: List<User> = arrayListOf()
    private lateinit var binding: UserListItemViewBinding

    fun setData (data: List<User>) {
=======
import ru.profitsw2000.githubclient.utils.OnItemClickListener
import ru.profitsw2000.githubclient.domain.entities.UserProfile
import ru.profitsw2000.githubclient.databinding.UserListItemViewBinding

class UserListAdapter(val onItemClickListener: OnItemClickListener): RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private var data: List<UserProfile> = arrayListOf()
    private lateinit var binding: UserListItemViewBinding

    fun setData (data: List<UserProfile>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = UserListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(user: UserDTO) {
=======
        fun bind(user: User) {
            with(binding){
                personPhotoImageView.load(user.avatar_url)
                loginTextView.text = user.login
                userTypeTextView.text = user.type
                root.setOnClickListener {
                    onItemClickListener.onItemClick(user)
=======
=======
        fun bind(userProfile: UserProfile) {
            with(binding){
                personNameTextView.text = userProfile.userName
                aboutPersonTextView.text = userProfile.userInfo
                personCityTextView.text = userProfile.userCity
                root.setOnClickListener {
                    onItemClickListener.onItemClick(userProfile)
                }
            }
        }
    }
}