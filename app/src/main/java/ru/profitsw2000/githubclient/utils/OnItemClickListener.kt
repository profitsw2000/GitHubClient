package ru.profitsw2000.githubclient.utils

import ru.profitsw2000.githubclient.domain.entities.UserProfile

interface OnItemClickListener {
    fun onItemClick(userProfile: UserProfile)
}