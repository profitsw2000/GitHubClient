package ru.profitsw2000.githubclient.ui.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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