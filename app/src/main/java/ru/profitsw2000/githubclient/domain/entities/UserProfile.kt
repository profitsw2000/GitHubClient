package ru.profitsw2000.githubclient.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserProfile(
    val id: Int,
    val userName: String,
    val userInfo: String,
    val userCity: String
) : Parcelable
