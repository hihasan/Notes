package com.hihasan.notes.payload

import lombok.Data

@Data
class DataDto{
    private val id: Long? = null
    private val title: String? = null
    private val description: String? = null
    private val content: String? = null
}