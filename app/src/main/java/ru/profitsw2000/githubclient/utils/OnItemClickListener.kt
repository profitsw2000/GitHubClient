package ru.profitsw2000.githubclient.utils

import ru.profitsw2000.githubclient.data.web.entities.UserDTO

interface OnItemClickListener {
    fun onItemClick(user: UserDTO)
}