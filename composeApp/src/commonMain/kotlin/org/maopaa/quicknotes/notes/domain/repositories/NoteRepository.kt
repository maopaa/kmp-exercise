package org.maopaa.quicknotes.notes.domain.repositories

import org.maopaa.quicknotes.notes.domain.entities.Note

interface NoteRepository {
    suspend fun getById(id: String): Result<Note>
    suspend fun getAll(): Result<List<Note>>
    suspend fun delete(id: String): Result<Unit>
    suspend fun insert(note: Note): Result<Note>
    suspend fun update(note: Note): Result<Note>
}