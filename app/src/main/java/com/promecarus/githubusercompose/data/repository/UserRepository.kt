package com.promecarus.githubusercompose.data.repository

import com.promecarus.githubusercompose.data.local.preference.SettingPreference
import com.promecarus.githubusercompose.data.remote.ApiService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class UserRepository(
    private val apiService: ApiService, private val settingPreference: SettingPreference,
) {
    fun search(query: String) =
        flow { emit(apiService.search(query, settingPreference.getSetting().first().result)) }

    fun detail(username: String) = flow { emit(apiService.detail(username)) }

    fun followers(username: String) = flow {
        emit(apiService.followers(username, settingPreference.getSetting().first().followers))
    }

    fun following(username: String) = flow {
        emit(apiService.following(username, settingPreference.getSetting().first().following))
    }
}
