package org.maopaa.kmp.notes.screen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.maopaa.kmp.notes.domain.repositories.NoteRepository

class AddNoteViewModel(
    private val repository: NoteRepository
): ScreenModel {

    private val _state = MutableStateFlow(NoteState())
    val state = _state.asStateFlow()

    fun onEvent(event: NoteEvent) {
        when(event) {
            is NoteEvent.add -> add(event)
        }
    }

    private fun add(event: NoteEvent.add) {
        if (_state.value.isLoading) return
        _state.update { it.copy(isLoading = true) }

        screenModelScope.launch {
            repository.insert(event.note)
                .onSuccess { _state.update { it.copy(isLoading = false, isSuccess = true) } }
                .onFailure { _state.update { it.copy(isLoading = false, isFailure = true) } }
        }

    }

}