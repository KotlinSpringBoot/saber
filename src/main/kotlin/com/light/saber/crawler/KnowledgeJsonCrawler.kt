package com.light.saber.crawler

import org.jsoup.nodes.Element

interface KnowledgeJsonCrawler {
    fun pageUrls(page: Int): String
    fun getPageJson(url: String): String
    fun getArticleBody(e: Element): String
    fun doCraw()
}