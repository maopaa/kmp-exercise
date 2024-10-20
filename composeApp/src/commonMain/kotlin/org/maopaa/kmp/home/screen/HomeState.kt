package org.maopaa.kmp.home.screen

import org.maopaa.kmp.notes.domain.entities.Note

data class HomeState(
    val list: List<Note> = emptyList(),
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
)
