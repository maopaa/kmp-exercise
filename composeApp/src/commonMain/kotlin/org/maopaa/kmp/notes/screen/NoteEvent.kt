package org.maopaa.kmp.notes.screen

import org.maopaa.kmp.notes.domain.entities.Note

sealed interface NoteEvent {

    data class add(val note: Note) : NoteEvent
}