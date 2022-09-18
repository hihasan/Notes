package com.hihasan.notes.repository

import com.hihasan.notes.entity.DataEntity
import org.springframework.data.jpa.repository.JpaRepository

interface DataRepository : JpaRepository<DataEntity, Long> {
}