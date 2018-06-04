package com.light.saber.crawler

interface CrawKnowledgeService {
    fun doSaveKnowledge(url: String, title: String?, content: String?)
}