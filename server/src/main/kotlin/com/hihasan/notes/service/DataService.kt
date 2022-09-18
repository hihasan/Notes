package com.hihasan.notes.service

import com.hihasan.notes.payload.DataDto
import com.hihasan.notes.payload.PostResponse


interface DataService {
    fun createPOst (dataDto: DataDto) : DataDto
    fun getAllNotes(pageBo : Int, pageSize: Int, sortBy : String, sortDir : String) : PostResponse
    fun getNotesById(id : Long) : DataDto
    fun updateNotes(dataDto: DataDto, id: Long) : DataDto
    fun deletePost(id: Long)
}