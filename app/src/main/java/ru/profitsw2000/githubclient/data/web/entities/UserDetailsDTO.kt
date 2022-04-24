package ru.profitsw2000.githubclient.data.web.entities

data class UserDetailsDTO(
    val login: String,
    val avatar_url: String,
    val name: String,
    val location: String
)
