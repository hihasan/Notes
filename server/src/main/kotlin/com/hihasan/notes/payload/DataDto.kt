package com.hihasan.notes.payload

import lombok.Data

@Data
class DataDto{
    var id: Long? = null
    var title: String? = null
    var notes: String? = null
}