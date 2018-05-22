package com.light.saber.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*
import javax.persistence.*

@Entity
class Knowledge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(length = 100, unique = true)
    var title = ""
    @Lob
    var answer = ""

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "GMT+8")
    var gmtCreate = Date()
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "GMT+8")
    var gmtModified = Date()
}
