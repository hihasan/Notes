package com.hihasan.notes.data.repository.impl

import com.hihasan.notes.data.dto.NoteDto
import com.hihasan.notes.data.repository.NoteRepository
import com.hihasan.notes.data.response.PostResponse

class OfflineNoteRepositoryImpl : NoteRepository {
    override fun createNote(noteDto: NoteDto): NoteDto {
        TODO("Not yet implemented")
    }

    override fun getAllNotes(
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        sortDir: String,
    ): PostResponse {
        TODO("Not yet implemented")
    }

    override fun getNotesById(id: Long): NoteDto {
        TODO("Not yet implemented")
    }

    override fun updateNotes(id: Long): NoteDto {
        TODO("Not yet implemented")
    }

    override fun deleteNotes(id: Long) {
        TODO("Not yet implemented")
    }
}