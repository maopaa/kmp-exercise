package org.maopaa.kmp.notes.data

import org.maopaa.kmp.notes.data.dto.NoteDto

class LocalSource {

    val list = mutableListOf<NoteDto>()

    fun insert(note: NoteDto): NoteDto {
        list.add(
            note.copy(
                id = (list.size + 1).toString()
            )
        )
        return note
    }

    suspend fun update(note: NoteDto): NoteDto {
        val index = list.indexOfFirst { it.id == note.id }
        list[index] = note
        return note
    }

    suspend fun delete(id: String) {
        list.removeAll( list.filter { it.id == id } )
    }

    suspend fun getById(id: String): NoteDto? {
        return list.find { it.id == id }
    }

    suspend fun getAll(): List<NoteDto> {
        return list
    }

}