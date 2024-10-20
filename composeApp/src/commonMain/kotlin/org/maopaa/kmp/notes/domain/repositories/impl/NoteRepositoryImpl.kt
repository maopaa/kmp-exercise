package org.maopaa.kmp.notes.domain.repositories.impl

import org.maopaa.kmp.notes.data.LocalSource
import org.maopaa.kmp.notes.data.dto.NoteDto
import org.maopaa.kmp.notes.domain.entities.Note
import org.maopaa.kmp.notes.domain.repositories.NoteRepository

class NoteRepositoryImpl(
    private val local: LocalSource
): NoteRepository {

    override suspend fun getById(id: String): Result<Note> =
        runCatching {
            local.getById(id)
                ?.toModel()
                ?: Note.empty()
        }

    override suspend fun getAll(): Result<List<Note>> =
        runCatching {
            local.getAll()
                .map { it.toModel() }
        }

    override suspend fun delete(id: String): Result<Unit> =
        runCatching {
            local.delete(id)
        }

    override suspend fun insert(note: Note): Result<Note> =
        runCatching {
            local.insert(NoteDto.fromModel(note))
                .toModel()
        }

    override suspend fun update(note: Note): Result<Note> =
        runCatching {
            local.update(NoteDto.fromModel(note))
                .toModel()
        }
}