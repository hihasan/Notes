package com.hihasan.notes.payload

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
class PostResponse{
    var content : List<DataDto> ?= null
    var pageNo : Int ?= null
    var pasgeSize : Int ?= null
    var totalElements : Long ?= null
    var totalPages : Int ?= null
    var last : Boolean ?= null
}
