package org.maopaa.kmp.notes.domain.entities

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
