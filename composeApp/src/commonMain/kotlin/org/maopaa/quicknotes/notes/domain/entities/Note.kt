package org.maopaa.quicknotes.notes.domain.entities

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.toLocalDateTime

data class Note(
    val id: String,
    val title: String,
    val content: String,
    val color: String,
    val createdAt: String
) {
    companion object {
        fun empty() = Note(
            id = "",
            title = "",
            content = "",
            color = "",
            createdAt = ""
        )
    }
}
