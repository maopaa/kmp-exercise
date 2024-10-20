package org.maopaa.quicknotes.notes.screen

import org.maopaa.quicknotes.notes.domain.entities.Note

data class NoteState(
    val note: Note = Note.empty(),
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isFailure: Boolean = false,
)
