package com.promecarus.githubusercompose.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.promecarus.githubusercompose.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert
    suspend fun addToFavorite(user: User)

    @Query("SELECT * FROM favorite_user ORDER BY id ASC")
    fun getAllFavorite(): Flow<List<User>>

    @Query("SELECT count(*) FROM favorite_user WHERE username = :username")
    fun checkFavorite(username: String): Flow<Int>

    @Query("DELETE FROM favorite_user WHERE username = :username")
    suspend fun removeFromFavorite(username: String)

    @Query("DELETE FROM favorite_user")
    suspend fun removeAllFavorite()
}
