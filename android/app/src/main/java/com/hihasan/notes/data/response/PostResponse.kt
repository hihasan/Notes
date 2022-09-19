package com.hihasan.notes.data.response

import com.hihasan.notes.data.dto.NoteDto

data class PostResponse (
    var content : List<NoteDto> ?= null,
    var pageNo : Int ?= null,
    var pasgeSize : Int ?= null,
    var totalElements : Long ?= null,
    var totalPages : Int ?= null,
    var last : Boolean ?= null,

)