package org.maopaa.quicknotes.notes.domain

import org.maopaa.quicknotes.notes.data.LocalSource
import org.maopaa.quicknotes.notes.domain.repositories.NoteRepository
import org.maopaa.quicknotes.notes.domain.repositories.impl.NoteRepositoryImpl

object NoteModule {
    val noteRepository: NoteRepository by lazy {
        NoteRepositoryImpl(local = LocalSource())
    }
}