package com.promecarus.githubusercompose.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorite_user")
data class User(
    @PrimaryKey val id: Int,
    @SerializedName("login") val username: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    val type: String,
)
