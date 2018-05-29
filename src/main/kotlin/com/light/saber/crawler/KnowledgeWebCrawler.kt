package com.light.saber.crawler

import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.StringUtils
import java.util.*

interface KnowledgeWebCrawler {
    fun pageUrls(page: Int): String
    fun getArticleListDocument(url: String, className: String): Elements
    fun getArticleUrl(e: Element, className: String): String
    fun getArticleTitle(e: Element, className: String): String
    fun getArticleBody(e: Element): String

    fun doCraw()
}