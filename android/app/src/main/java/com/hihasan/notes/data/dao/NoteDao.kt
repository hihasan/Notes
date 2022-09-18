package com.hihasan.notes.data.dao

import androidx.room.Dao
import com.hihasan.notes.data.entity.NoteEntity
import com.hihasan.notes.utils.base.BaseDao

@Dao
interface NoteDao : BaseDao<NoteEntity> {
}