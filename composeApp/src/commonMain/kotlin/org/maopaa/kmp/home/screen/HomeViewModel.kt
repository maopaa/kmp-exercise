package org.maopaa.kmp.home.screen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.maopaa.kmp.notes.domain.repositories.NoteRepository

class HomeViewModel(
    private val noteRepository: NoteRepository
): ScreenModel {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.refreshList -> getAllNotes()
        }
    }

    private fun getAllNotes() {
        if (state.value.isLoading) return
        _state.update { it.copy(isLoading = true) }

        screenModelScope.launch {
            noteRepository.getAll().onSuccess { notes ->
                _state.update { it.copy(list = notes, isLoading = false) }
            }.onFailure {
                _state.update { it.copy(isLoading = false, isFailure = true) }
            }
        }
    }
}