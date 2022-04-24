package ru.profitsw2000.githubclient.ui.screens.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.profitsw2000.githubclient.databinding.UserRepositoryListItemViewBinding

class UserRepositoriesAdapter : RecyclerView.Adapter<UserRepositoriesAdapter.ViewHolder>(){

    private lateinit var binding: UserRepositoryListItemViewBinding
    private var data: List<String> = arrayListOf()

    fun setData (data: List<String>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserRepositoriesAdapter.ViewHolder {
        binding = UserRepositoryListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: UserRepositoriesAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(userRepo: String) {
            with(binding){
                userRepositoryNameTextView.text = userRepo
            }
        }
    }
}