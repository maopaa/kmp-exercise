package org.maopaa.kmp.notes.data.dto

import kotlinx.datetime.LocalDateTime
import org.maopaa.kmp.notes.domain.entities.Note

data class NoteDto (
    val id: String,
    val title: String,
    val description: String,
    val color: String,
    val createdAt: String
) {
    fun toModel() = Note(
        id = id,
        title = title,
        content = description,
        color = color,
        createdAt = createdAt
    )

    companion object {
        fun fromModel(note: Note) = NoteDto(
            id = note.id,
            title = note.title,
            description = note.content,
            color = note.color,
            createdAt = note.createdAt
        )
    }
}