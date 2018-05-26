package com.light.saber.model

import javax.persistence.*

@Entity
class CrawSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(length = 10)
    var type = ""

    @Column(length = 200, unique = true)
    var url = ""

}
