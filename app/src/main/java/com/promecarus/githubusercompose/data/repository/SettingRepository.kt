package com.promecarus.githubusercompose.data.repository

import com.promecarus.githubusercompose.data.local.preference.SettingPreference
import com.promecarus.githubusercompose.data.model.Setting

class SettingRepository(private val settingPreference: SettingPreference) {
    fun getSetting() = settingPreference.getSetting()

    suspend fun setSetting(setting: Setting) = settingPreference.setSetting(setting)
}
