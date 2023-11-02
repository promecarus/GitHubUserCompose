package com.promecarus.githubusercompose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.promecarus.githubusercompose.data.model.Detail
import com.promecarus.githubusercompose.data.model.User
import com.promecarus.githubusercompose.data.repository.FavoriteRepository
import com.promecarus.githubusercompose.data.repository.UserRepository
import com.promecarus.githubusercompose.ui.helper.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailViewModel(
    private val userRepository: UserRepository,
    private val favoriteRepository: FavoriteRepository,
) : ViewModel() {
    private val _stateDetail = MutableStateFlow<State<Detail>>(State.Loading)
    val stateDetail get() = _stateDetail.asStateFlow()

    private val _stateFollowers = MutableStateFlow<State<List<User>>>(State.Loading)
    val stateFollowers get() = _stateFollowers.asStateFlow()

    private val _stateFollowing = MutableStateFlow<State<List<User>>>(State.Loading)
    val stateFollowing get() = _stateFollowing.asStateFlow()

    private var username = ""

    fun getDetail(username: String) {
        this.username = username
        viewModelScope.launch {
            userRepository.detail(username)
                .catch { _stateDetail.value = State.Error(it.message ?: "Unknown error") }
                .collect { _stateDetail.value = State.Success(it) }
        }
    }

    fun getFollowers(username: String) = viewModelScope.launch {
        userRepository.followers(username)
            .catch { _stateFollowers.value = State.Error(it.message ?: "Unknown error") }
            .collect { _stateFollowers.value = State.Success(it) }
    }

    fun getFollowing(username: String) = viewModelScope.launch {
        userRepository.following(username)
            .catch { _stateFollowing.value = State.Error(it.message ?: "Unknown error") }
            .collect { _stateFollowing.value = State.Success(it) }
    }

    fun refresh() = username.let {
        getDetail(it)
        getFollowers(it)
        getFollowing(it)
    }

    fun addToFavorite(user: User) = viewModelScope.launch { favoriteRepository.addToFavorite(user) }

    fun checkFavorite(username: String) = favoriteRepository.checkFavorite(username)

    fun removeFromFavorite(username: String) =
        viewModelScope.launch { favoriteRepository.removeFromFavorite(username) }
}
