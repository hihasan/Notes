package com.hihasan.notes.utils.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj : T) : Long

//    @Insert
//    suspend fun insert(obj: T) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(obj: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg obj : T)

    @Update
    suspend fun update(obj: T)

    @Delete
    suspend fun delete(obj: T)
}