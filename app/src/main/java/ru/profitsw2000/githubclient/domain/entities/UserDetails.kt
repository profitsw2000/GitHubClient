package ru.profitsw2000.githubclient.domain.entities

data class UserDetails(
    val login: String,
    val avatar_url: String,
    val name: String,
    val location: String
)
