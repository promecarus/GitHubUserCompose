package com.promecarus.githubusercompose.data.remote

import com.promecarus.githubusercompose.data.model.Detail
import com.promecarus.githubusercompose.data.model.Search
import com.promecarus.githubusercompose.data.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    suspend fun search(
        @Query("q") query: String,
        @Query("per_page") perPage: Int,
    ): Search

    @GET("users/{username}")
    suspend fun detail(
        @Path("username") username: String,
    ): Detail

    @GET("users/{username}/followers")
    suspend fun followers(
        @Path("username") username: String,
        @Query("per_page") perPage: Int,
    ): List<User>

    @GET("users/{username}/following")
    suspend fun following(
        @Path("username") username: String,
        @Query("per_page") perPage: Int,
    ): List<User>
}
