package com.promecarus.githubusercompose.data.repository

import com.promecarus.githubusercompose.data.local.database.FavoriteDatabase
import com.promecarus.githubusercompose.data.model.User

class FavoriteRepository(private val favoriteDatabase: FavoriteDatabase) {
    suspend fun addToFavorite(user: User) = favoriteDatabase.favoriteDao().addToFavorite(user)

    fun getAllFavorite() = favoriteDatabase.favoriteDao().getAllFavorite()

    fun checkFavorite(username: String) = favoriteDatabase.favoriteDao().checkFavorite(username)

    suspend fun removeFromFavorite(username: String) =
        favoriteDatabase.favoriteDao().removeFromFavorite(username)

    suspend fun removeAllFavorite() = favoriteDatabase.favoriteDao().removeAllFavorite()
}
