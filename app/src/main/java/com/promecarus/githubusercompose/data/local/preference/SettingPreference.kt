package com.promecarus.githubusercompose.data.local.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.promecarus.githubusercompose.data.model.Setting
import kotlinx.coroutines.flow.map

class SettingPreference(private val dataStore: DataStore<Preferences>) {
    suspend fun setSetting(setting: Setting) {
        dataStore.edit {
            it[RESULT] = setting.result
            it[FOLLOWERS] = setting.followers
            it[FOLLOWING] = setting.following
        }
    }

    fun getSetting() = dataStore.data.map {
        Setting(
            it[RESULT] ?: 20,
            it[FOLLOWERS] ?: 10,
            it[FOLLOWING] ?: 10,
        )
    }

    companion object {
        private val RESULT = intPreferencesKey("result")
        private val FOLLOWERS = intPreferencesKey("followers")
        private val FOLLOWING = intPreferencesKey("following")
    }
}
