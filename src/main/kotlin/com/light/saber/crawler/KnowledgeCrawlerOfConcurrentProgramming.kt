package com.light.saber.crawler

import com.alibaba.microtek.crawler.KnowledgeWebCrawler
import com.light.saber.service.KnowledgeService
import com.light.saber.webclient.CrawlerWebClient
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class KnowledgeCrawlerOfConcurrentProgramming : KnowledgeWebCrawler {
    @Autowired
    lateinit var KnowledgeService: KnowledgeService
    @Autowired
    lateinit var CrawlerWebClient: CrawlerWebClient

    override fun getArticleBody(e: Element) = e.getElementsByClass("post_content").html()

    override fun pageUrls(page: Int): String = "http://ifeve.com/page/${page}"

    override fun getArticleListDocument(url: String, className: String): Elements {
        val articlePageHtml = CrawlerWebClient.getPageHtmlText(url)
        val articlePageDocument = Jsoup.parse(articlePageHtml)
        return articlePageDocument.getElementsByClass(className)
    }

    override fun getArticleUrl(e: Element) = e.child(0).attr("href")

    override fun getArticleTitle(e: Element) = e.child(0).html()

    @Scheduled(cron = "0 30 1 1/1 * ?")
    override fun doCraw() {
        for (p in 1..103) {
            val pageUrl = pageUrls(p)
            val pageList = getArticleListDocument(pageUrl, "title")
            pageList.forEach {
                try {
                    val articleUrl = getArticleUrl(it)
                    val articleTitle = getArticleTitle(it)
                    val articleHTML = CrawlerWebClient.getPageHtmlText(articleUrl)
                    val articleDocument = Jsoup.parse(articleHTML)
                    val articleBody = getArticleBody(articleDocument)
                    println(articleTitle)
                    println(articleUrl)
                    KnowledgeService.doSaveKnowledge(articleUrl, articleTitle, articleBody)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}