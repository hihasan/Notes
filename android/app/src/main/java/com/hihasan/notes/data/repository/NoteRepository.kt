package com.hihasan.notes.data.repository

import com.hihasan.notes.data.dto.NoteDto
import com.hihasan.notes.data.response.PostResponse

interface NoteRepository {
    fun createNote(noteDto: NoteDto) : NoteDto
    fun getAllNotes(pageNo : Int, pageSize : Int, sortBy: String, sortDir : String) : PostResponse
    fun getNotesById(id : Long) : NoteDto
    fun updateNotes(id : Long) : NoteDto
    fun deleteNotes(id : Long)
}