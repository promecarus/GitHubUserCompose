package com.promecarus.githubusercompose.data.repository

import com.promecarus.githubusercompose.data.local.preference.HistoryPreference

class HistoryRepository(private val historyPreference: HistoryPreference) {
    fun getHistory() = historyPreference.getHistory()

    suspend fun setHistory(newHistory: String) = historyPreference.setHistory(newHistory)
}
