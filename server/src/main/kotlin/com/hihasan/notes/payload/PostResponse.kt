package com.hihasan.notes.payload

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
data class PostResponse(
    val content : List<DataDto>,
    val pageNo : Integer,
    val pasgeSize : Integer,
    val totalElements : Long,
    val totalPages : Integer,
    val last : Boolean
)
