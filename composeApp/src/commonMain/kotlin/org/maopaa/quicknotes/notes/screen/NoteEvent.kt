package org.maopaa.quicknotes.notes.screen

import org.maopaa.quicknotes.notes.domain.entities.Note

sealed interface NoteEvent {

    data class add(val note: Note) : NoteEvent
}