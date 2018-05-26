package com.light.saber.dao

import com.light.saber.model.Knowledge
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface KnowledgeDao : JpaRepository<Knowledge, Long> {

    // JPQL里面没有limit
    @Query(value = "SELECT * FROM knowledge WHERE title = :title limit 1", nativeQuery = true)
    fun selectByTitle(title: String): Knowledge?

    @Query("SELECT a FROM #{#entityName} a where a.title like concat('%', :title, '%')")
    fun page(title: String?, page: Pageable): Page<Knowledge>

    @Query("SELECT a FROM #{#entityName} a")
    fun page(page: Pageable): Page<Knowledge>

    @Query("select count(*) from #{#entityName} a where a.url = :url")
    abstract fun countByUrl(url: String): Int

}
