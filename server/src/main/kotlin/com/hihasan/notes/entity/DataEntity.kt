package com.hihasan.notes.entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
    name = "tbl_notes"
)
data class DataEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "col_title", nullable = false)
    val title : String,

    @Column(name = "col_notes", nullable = false)
    val notes: String

)