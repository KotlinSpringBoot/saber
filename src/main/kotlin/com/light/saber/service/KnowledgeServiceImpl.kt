package com.light.saber.service

import com.light.saber.dao.KnowledgeDao
import com.light.saber.model.Knowledge
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import java.util.*

@Service("knowledgeService")
class KnowledgeServiceImpl : KnowledgeService {
    override fun doSaveKnowledge(url: String, title: String, content: String?) {
        if (StringUtils.isEmpty(url) || StringUtils.isEmpty(title) || StringUtils.isEmpty(content)) {
            return
        }

        val Knowledge = Knowledge()
        Knowledge.title = title ?: ""
        Knowledge.content = content ?: ""
        Knowledge.gmtCreate = Date()
        Knowledge.gmtModified = Date()
        try {
            KnowledgeDao.save(Knowledge)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Autowired lateinit var KnowledgeDao: KnowledgeDao
    override fun page(title: String?, page: Pageable): Page<Knowledge> {
        if (StringUtils.isEmpty(title)) {
            return KnowledgeDao.page(page)
        } else {
            return KnowledgeDao.page(title, page)
        }
    }
}
