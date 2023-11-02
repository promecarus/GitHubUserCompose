package com.promecarus.githubusercompose.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.promecarus.githubusercompose.data.model.User
import com.promecarus.githubusercompose.data.repository.HistoryRepository
import com.promecarus.githubusercompose.data.repository.UserRepository
import com.promecarus.githubusercompose.ui.helper.State
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ListViewModel(
    private val userRepository: UserRepository,
    private val historyRepository: HistoryRepository,
) : ViewModel() {
    private val _state = MutableStateFlow<State<List<User>>>(State.Loading)
    val state: StateFlow<State<List<User>>> get() = _state

    private var _query by mutableStateOf("")
    val query: String get() = _query

    private var searchJob: Job? = null

    init {
        search(runBlocking { getHistory().first()[0] })
    }

    fun search(username: String) {
        _query = username

        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            delay(250)
            _state.value = State.Loading
            userRepository.search(username)
                .catch { _state.value = State.Error(it.message ?: "Unknown error") }
                .collect { _state.value = State.Success(it.items) }
        }
    }

    fun refresh() = search(query)

    fun setHistory(newHistory: String) =
        viewModelScope.launch { historyRepository.setHistory(newHistory) }

    fun getHistory() = historyRepository.getHistory()
}
