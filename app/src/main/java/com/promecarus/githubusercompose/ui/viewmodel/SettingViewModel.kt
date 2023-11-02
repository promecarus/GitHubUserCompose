package com.promecarus.githubusercompose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.promecarus.githubusercompose.data.model.Setting
import com.promecarus.githubusercompose.data.repository.SettingRepository
import kotlinx.coroutines.launch

class SettingViewModel(private val settingRepository: SettingRepository) : ViewModel() {
    fun getSetting() = settingRepository.getSetting()

    fun setSetting(setting: Setting) =
        viewModelScope.launch { settingRepository.setSetting(setting) }
}
