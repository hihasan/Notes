package com.hihasan.notes.service

import com.hihasan.notes.payload.DataDto


interface DataService {
    fun createPOst (dataDto: DataDto) : DataDto
    fun getAllNotes(pageBo : Integer, pageSize: Integer, sortBy : String, sortDir : String)
    fun getNotesById(id : Long) : DataDto
    fun updateNotes(dataDto: DataDto, id: Long)
    fun deletePost(id: Long) : Void
}