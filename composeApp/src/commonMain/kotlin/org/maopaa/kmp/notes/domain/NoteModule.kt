package org.maopaa.kmp.notes.domain

import org.maopaa.kmp.notes.data.LocalSource
import org.maopaa.kmp.notes.domain.repositories.NoteRepository
import org.maopaa.kmp.notes.domain.repositories.impl.NoteRepositoryImpl

object NoteModule {
    val noteRepository: NoteRepository by lazy {
        NoteRepositoryImpl(local = LocalSource())
    }
}