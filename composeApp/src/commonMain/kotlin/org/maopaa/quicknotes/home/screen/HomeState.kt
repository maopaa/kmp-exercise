package org.maopaa.quicknotes.home.screen

import org.maopaa.quicknotes.notes.domain.entities.Note

data class HomeState(
    val list: List<Note> = emptyList(),
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
)
