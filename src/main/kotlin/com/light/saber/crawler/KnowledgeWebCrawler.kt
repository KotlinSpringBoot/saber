package com.alibaba.microtek.crawler

import org.jsoup.nodes.Element
import org.jsoup.select.Elements

interface KnowledgeWebCrawler {
    fun pageUrls(page: Int): String
    fun getArticleListDocument(url: String, className: String): Elements
    fun getArticleUrl(e: Element): String
    fun getArticleTitle(e: Element): String
    fun getArticleBody(e: Element): String

    fun doCraw()
}