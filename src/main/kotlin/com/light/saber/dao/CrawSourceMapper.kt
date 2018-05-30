package com.light.saber.dao

import com.light.saber.model.CrawSource
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CrawSourceMapper : JpaRepository<CrawSource, Long>{

    @Query("select a from #{#entityName} a where a.type = 'JIAN_SHU' order by a.id desc")
    fun findJianShu():List<CrawSource>
}
