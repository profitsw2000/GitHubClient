package ru.profitsw2000.githubclient.utils

import ru.profitsw2000.githubclient.data.web.entities.UserDTO

interface OnItemClickListener {
    fun onItemClick(user: UserDTO)
=======
=======
import ru.profitsw2000.githubclient.domain.entities.User

interface OnItemClickListener {
    fun onItemClick(user: User)
=======
import ru.profitsw2000.githubclient.domain.entities.UserProfile

interface OnItemClickListener {
    fun onItemClick(userProfile: UserProfile)
}