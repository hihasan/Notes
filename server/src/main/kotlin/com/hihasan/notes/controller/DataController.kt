package com.hihasan.notes.controller

import com.hihasan.notes.entity.DataEntity
import com.hihasan.notes.payload.DataDto
import com.hihasan.notes.payload.PostResponse
import com.hihasan.notes.service.DataService
import com.hihasan.notes.utils.AppConstant
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/notes")
class DataController(dataService: DataService) {
    lateinit var dataService: DataService

    init {
        this.dataService = dataService
    }

    @PostMapping
    fun postNotes(@RequestBody postDto: DataDto) : ResponseEntity<DataDto>{
        return ResponseEntity<DataDto>(dataService.createPOst(postDto), HttpStatus.CREATED)
    }

    @GetMapping
    fun getAllNotes(
        @RequestParam(value = "pageNo", defaultValue = AppConstant.DEFAULT_PAGE_NUMBER, required = false)  pageNo : Int,
        @RequestParam(value = "pageSize", defaultValue = AppConstant.DEFAULT_PAGE_SIZE, required = false) pageSize : Int,
        @RequestParam(value = "sortBy", defaultValue = AppConstant.DEFAULT_SORT_BY, required = false) sortBy : String,
        @RequestParam(value = "sortDir", defaultValue = AppConstant.DEFAULT_SORT_DIRECTION, required = false) sortDir : String
    ) : PostResponse{
        return dataService.getAllNotes(pageNo, pageSize, sortBy, sortDir)
    }

    @GetMapping( "/{id}")
    fun getNotesBtId(@PathVariable("id") id : Long) : ResponseEntity<DataDto>{
        return ResponseEntity<DataDto>(dataService.getNotesById(id), HttpStatus.OK)
    }

    @PutMapping( "/{id}")
    fun updateNotes(@RequestBody dataDto: DataDto, @PathVariable("id") id : Long) : ResponseEntity<DataDto>{
        val postResponse : DataDto = dataService.updateNotes(dataDto, id)
        return ResponseEntity<DataDto>(postResponse, HttpStatus.OK)
    }

    @DeleteMapping( "/{id}")
    fun deleteNotes(@PathVariable("id") id : Long) : ResponseEntity<String>{
        dataService.deletePost(id)

        return ResponseEntity<String>("You Have Successfully Deleted The Note", HttpStatus.OK)
    }
}