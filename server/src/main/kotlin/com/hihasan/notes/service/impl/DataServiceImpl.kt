package com.hihasan.notes.service.impl

import com.hihasan.notes.payload.DataDto
import com.hihasan.notes.repository.DataRepository
import com.hihasan.notes.service.DataService
import org.springframework.stereotype.Service

@Service
class DataServiceImpl(dataRepository: DataRepository) : DataService {
    private val dataRepository : DataRepository

    init {
        this.dataRepository = dataRepository
    }

    override fun createPOst(dataDto: DataDto): DataDto {
        TODO("Not yet implemented")
    }

    override fun getAllNotes(pageBo: Integer, pageSize: Integer, sortBy: String, sortDir: String) {
        TODO("Not yet implemented")
    }

    override fun getNotesById(id: Long): DataDto {
        TODO("Not yet implemented")
    }

    override fun updateNotes(dataDto: DataDto, id: Long) {
        TODO("Not yet implemented")
    }

    override fun deletePost(id: Long): Void {
        TODO("Not yet implemented")
    }
}