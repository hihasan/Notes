package com.hihasan.notes.data.repository.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hihasan.notes.api.NotesApi
import com.hihasan.notes.data.dto.NoteDto
import com.hihasan.notes.data.repository.NoteRepository
import com.hihasan.notes.data.response.PostResponse
import com.hihasan.notes.utils.Response
import java.lang.Exception

class OnlineNoteRepositoryImpl(val notesApi: NotesApi) : NoteRepository{
    private val createNoteLiveData = MutableLiveData<Response<NoteDto>>()

    val createNote : LiveData<Response<NoteDto>>
    get() = createNoteLiveData

    override suspend fun createNote(noteDto: NoteDto): NoteDto {
        try {
            createNoteLiveData.postValue(Response.Loading())
            val response = notesApi.createPost(noteDto.title, noteDto.notes)

            if (response != null){
                createNoteLiveData.postValue(Response.Success(noteDto))
            }
        } catch (e : Exception){
            createNoteLiveData.postValue(Response.Error("Format Need to Check"))
        }

        return noteDto
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