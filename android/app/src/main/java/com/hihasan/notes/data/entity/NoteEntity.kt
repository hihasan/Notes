package com.hihasan.notes.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_notes")
data class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    var id : Long
)