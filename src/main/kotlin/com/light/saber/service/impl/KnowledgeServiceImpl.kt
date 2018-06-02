package com.light.saber.service.impl

import com.light.saber.dao.KnowledgeMapper
import com.light.saber.model.Knowledge
import com.light.saber.service.KnowledgeService
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

        val keyWords =

        try {
            KnowledgeMapper.save(Knowledge)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Autowired lateinit var KnowledgeMapper: KnowledgeMapper
    override fun page(title: String?, page: Pageable): Page<Knowledge> {
        if (StringUtils.isEmpty(title)) {
            return KnowledgeMapper.page(page)
        } else {
            return KnowledgeMapper.page(title, page)
        }
    }
}
