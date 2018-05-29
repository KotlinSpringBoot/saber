package com.light.saber.service

import com.light.saber.model.Knowledge
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface KnowledgeService {
    abstract fun page(title: String?, page: Pageable): Page<Knowledge>
    fun doSaveKnowledge(articleUrl: String, articleTitle: String, articleBody: String?)

}
