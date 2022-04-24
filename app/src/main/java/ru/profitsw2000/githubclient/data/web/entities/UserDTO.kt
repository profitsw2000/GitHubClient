package ru.profitsw2000.githubclient.data.web.entities

data class UserDTO(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val type: String
)
