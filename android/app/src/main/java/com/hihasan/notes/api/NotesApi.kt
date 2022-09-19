package com.hihasan.notes.api

import com.hihasan.notes.constant.APiConstant
import com.hihasan.notes.data.dto.NoteDto
import com.hihasan.notes.data.response.PostResponse
import retrofit2.http.GET
import retrofit2.http.POST

//need to work on here
interface NotesApi {

    @GET(APiConstant.NOTES)
    suspend fun getAllNotes() : List<PostResponse>

    @POST(APiConstant.NOTES)
    suspend fun createPost() : NoteDto
}