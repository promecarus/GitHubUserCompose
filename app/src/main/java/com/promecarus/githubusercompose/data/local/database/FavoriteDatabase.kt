package com.promecarus.githubusercompose.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.promecarus.githubusercompose.data.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
