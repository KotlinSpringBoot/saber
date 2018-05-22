package com.light.saber.service

import com.light.saber.dao.KnowledgeDao
import com.light.saber.model.Knowledge
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils

@Service
class KnowledgeServiceImpl : KnowledgeService {
    @Autowired lateinit var KnowledgeDao: KnowledgeDao
    override fun page(title: String?, page: Pageable): Page<Knowledge> {
        if (StringUtils.isEmpty(title)) {
            return KnowledgeDao.page(page)
        } else {
            return KnowledgeDao.page(title, page)
        }
    }
}
