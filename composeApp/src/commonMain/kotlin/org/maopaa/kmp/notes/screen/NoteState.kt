package org.maopaa.kmp.notes.screen

import org.maopaa.kmp.notes.domain.entities.Note

data class NoteState(
    val note: Note = Note.empty(),
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isFailure: Boolean = false,
)
