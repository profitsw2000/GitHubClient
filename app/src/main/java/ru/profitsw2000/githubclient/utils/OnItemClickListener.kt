package ru.profitsw2000.githubclient.utils

import ru.profitsw2000.githubclient.domain.entities.User

interface OnItemClickListener {
    fun onItemClick(user: User)
}