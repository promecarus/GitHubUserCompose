package com.promecarus.githubusercompose.data.model

import com.google.gson.annotations.SerializedName

data class Detail(
    val id: Int,
    @SerializedName("login") val username: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    val type: String = "User",
    val name: String?,
    val email: String?,
    val followers: Int? = null,
    val following: Int? = null,
    @SerializedName("public_repos") val publicRepos: Int? = null,
    @SerializedName("public_gists") val publicGists: Int? = null,
)
