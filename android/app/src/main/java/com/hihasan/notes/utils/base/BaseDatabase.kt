package com.hihasan.notes.utils.base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hihasan.notes.constant.DatabaseConstants
import com.hihasan.notes.data.dao.NoteDao
import com.hihasan.notes.data.entity.NoteEntity

@Database(
    entities = [
        NoteEntity::class
    ],
    version = DatabaseConstants.DATABASE_VERSION,
    exportSchema = false
)

abstract class BaseDatabase : RoomDatabase() {
    abstract val noteDao : NoteDao

    companion object {
        private var INSTANCE: BaseDatabase? = null

        fun getDatabase(ctx: Context): BaseDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        ctx, BaseDatabase::class.java, DatabaseConstants.DATABASE_NAME
                    ).build()
                }
            }

            return INSTANCE!!
        }
    }


}