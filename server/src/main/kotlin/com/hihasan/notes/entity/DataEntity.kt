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
class DataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?=null

    @Column(name = "col_title", nullable = false)
    var title : String?= null

    @Column(name = "col_notes", nullable = false)
    var notes: String?=null



}