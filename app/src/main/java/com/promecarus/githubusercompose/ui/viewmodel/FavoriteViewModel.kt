package com.promecarus.githubusercompose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.promecarus.githubusercompose.data.repository.FavoriteRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(private val favoriteRepository: FavoriteRepository) : ViewModel() {
    fun getAllFavorite() = favoriteRepository.getAllFavorite()

    fun removeAllFavorite() = viewModelScope.launch { favoriteRepository.removeAllFavorite() }
}
