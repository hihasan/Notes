package com.hihasan.notes.service.impl



import com.hihasan.notes.entity.DataEntity
import com.hihasan.notes.exception.ResourceNotFoundException
import com.hihasan.notes.payload.DataDto
import com.hihasan.notes.payload.PostResponse
import com.hihasan.notes.repository.DataRepository
import com.hihasan.notes.service.DataService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.function.Function
import java.util.stream.Collectors


@Service
class DataServiceImpl(dataRepository: DataRepository) : DataService {
    private val dataRepository : DataRepository

    init {
        this.dataRepository = dataRepository
    }

    override fun createPOst(dataDto: DataDto): DataDto {
        val notes : DataEntity = mapEntityToDto(dataDto)
        val newNotes : DataEntity = dataRepository.save(notes)

        return mapDtoToEntity(newNotes)
    }

    override fun getAllNotes(pageBo: Int, pageSize: Int, sortBy: String, sortDir: String): PostResponse {
        val sort: Sort =
            if (sortDir.equals(Sort.Direction.ASC.name, ignoreCase = true)) Sort.by(sortBy).ascending() else Sort.by(
                sortBy
            ).descending()

        val pageable: Pageable = PageRequest.of(pageBo, pageSize, sort)
        val notes : Page<DataEntity> = dataRepository.findAll(pageable)

        //get All List Content
        val listOfNotes : List<DataEntity> = notes.content


        val content : List<DataDto> = listOfNotes.stream()
            .map { mapDtoToEntity(data = DataEntity()) }
            .collect(Collectors.toList())

        val noteResponse = PostResponse()

        noteResponse.content = content
        noteResponse.pageNo = notes.number
        noteResponse.pasgeSize = notes.size
        noteResponse.totalElements = notes.totalElements
        noteResponse.totalPages = notes.totalPages
        noteResponse.last = notes.isLast

        return noteResponse


    }

    override fun getNotesById(id: Long): DataDto {
        var notes : DataEntity = dataRepository.findById(id).orElseThrow() { ResourceNotFoundException("notes", "id", id) }

        return mapDtoToEntity(notes)
    }

    override fun updateNotes(dataDto: DataDto, id: Long): DataDto {
        val notes : DataEntity = dataRepository.findById(id).orElseThrow() { ResourceNotFoundException("notes", "id", id) }

        notes.title = dataDto.title
        notes.notes = dataDto.notes

        val noteUpdate : DataEntity = dataRepository.save(notes);

        return mapDtoToEntity(noteUpdate)
    }

    override fun deletePost(id: Long) {
        val notes : DataEntity = dataRepository.findById(id).orElseThrow() { ResourceNotFoundException("notes", "id", id) }
        dataRepository.delete(notes)
    }

    private fun mapDtoToEntity(data : DataEntity) : DataDto{
        val dataDto = DataDto()
        dataDto.id = data.id
        dataDto.title = data.title
        dataDto.notes = data.notes

        return dataDto
    }

    private fun mapEntityToDto(data : DataDto) : DataEntity{
        val dataEntity = DataEntity()
        dataEntity.id = data.id
        dataEntity.title = data.title
        dataEntity.notes = data.notes

        return dataEntity
    }
}