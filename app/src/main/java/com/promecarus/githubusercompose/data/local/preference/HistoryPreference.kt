package com.promecarus.githubusercompose.data.local.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map

class HistoryPreference(private val dataStore: DataStore<Preferences>) {
    suspend fun setHistory(newHistory: String) {
        dataStore.edit {
            it[HISTORY_1] = it[HISTORY_2] ?: ""
            it[HISTORY_2] = it[HISTORY_3] ?: ""
            it[HISTORY_3] = it[HISTORY_4] ?: ""
            it[HISTORY_4] = newHistory
        }
    }

    fun getHistory() = dataStore.data.map {
        listOf(
            it[HISTORY_4] ?: "promec",
            it[HISTORY_3] ?: "eyegnieeslla",
            it[HISTORY_2] ?: "mojombo",
            it[HISTORY_1] ?: "git",
        )
    }

    companion object {
        private val HISTORY_1 = stringPreferencesKey("history_1")
        private val HISTORY_2 = stringPreferencesKey("history_2")
        private val HISTORY_3 = stringPreferencesKey("history_3")
        private val HISTORY_4 = stringPreferencesKey("history_4")
    }
}
