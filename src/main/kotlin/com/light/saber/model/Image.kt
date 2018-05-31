package com.light.saber.model

import java.util.*
import javax.persistence.*

@Entity
@Table(indexes = arrayOf(
        Index(name = "idx_url", unique = true, columnList = "url"),
        Index(name = "idx_category", unique = false, columnList = "category")))
class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1
    @Version
    var version: Int = 0

    @Column(length = 255)
    var category: String = ""
    var isFavorite: Int = 0

    @Column(length = 255, unique = true, nullable = false)
    var url: String = ""

    var gmtCreated: Date = Date()
    var gmtModified: Date = Date()
    var isDeleted: Int = 0  //1 Yes 0 No
    var deletedDate: Date = Date()

    @Lob
    var imageBlob: ByteArray = byteArrayOf()
    /* 0-Baidu  1-Gank  2-HuaBan*/
    var sourceType: Int = 0

    override fun toString(): String {
        return "Image(id=$id, version=$version, category='$category', isFavorite=$isFavorite, url='$url', gmtCreated=$gmtCreated, gmtModified=$gmtModified, isDeleted=$isDeleted, deletedDate=$deletedDate)"
    }
}
