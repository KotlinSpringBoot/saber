package com.light.saber.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*
import javax.persistence.*

@Entity
@Table(indexes = [
    Index(name = "uk_title", unique = true, columnList = "title"),
    Index(name = "idx_keyWords", columnList = "keyWords")
])
class Knowledge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(length = 200, unique = true, nullable = false)
    var title = ""

    @Column(length = 200)
    var url = ""

    @Lob
    @Column(nullable = false)
    var content = ""

    @Column(length = 200)
    var keyWords = ""

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "GMT+8")
    var gmtCreate = Date()
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "GMT+8")
    var gmtModified = Date()
}
